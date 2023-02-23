package com.gupao.edu.account.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.entity.UserLoginHistory;
import com.gupao.edu.account.client.req.BindMobileReq;
import com.gupao.edu.account.client.req.BindThirdReq;
import com.gupao.edu.account.client.req.ChangeMobileReq;
import com.gupao.edu.account.client.req.UnbindThirdReq;
import com.gupao.edu.account.client.resp.AccountSecurityResp;
import com.gupao.edu.account.client.resp.LoginResp;
import com.gupao.edu.account.client.resp.MessageCountResp;
import com.gupao.edu.account.server.enums.CacheKeyEnum;
import com.gupao.edu.account.server.enums.UserEnums;
import com.gupao.edu.account.server.jwt.JwtTokenUtils;
import com.gupao.edu.account.server.mapper.UserMapper;
import com.gupao.edu.account.server.service.CommonService;
import com.gupao.edu.account.server.service.UserLoginHistoryService;
import com.gupao.edu.account.server.service.UserService;
import com.gupao.edu.account.server.utils.PhoneNumberUtil;
import com.gupao.edu.account.server.utils.ThirdPartyLoginHelper;
import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.RedisService;
import com.gupao.edu.common.cache.key.SmsPrefix;
import com.gupao.edu.common.cache.key.UserPrefix;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.common.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


	@Value("${user.password.salt:123456}")
	private String passwordSalt;

	@Autowired
	private CommonService commonService;

	@Value("${user.jwt.secret:abcd}")
	private String secret;
	@Autowired
	ThirdPartyLoginHelper thirdPartyLoginHelper;
	@Autowired
	private UserLoginHistoryService userLoginHistoryService;

	/**
	 * 根据手机号查询
	 */
	@Override
	public User getByMobile(String mobile) {
		User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("mobile", mobile));
		return user;
	}

	/**
	 * 根据用户唯一编码查询
	 */
	@Override
	public User getByUserUniqueCode(String userUniqueCode) {
		return this.baseMapper.selectOne(new QueryWrapper<User>().eq("user_unique_code", userUniqueCode));
	}

	/**
	 * 用户注册
	 *
	 * @param mobile   手机号
	 * @param password 密码
	 * @param bizCode 业务编码
	 * @param sms 验证码
	 * @return
	 */
	@Override
	public Result<LoginResp> registerUser(String mobile, String password,Integer bizCode,String sms) {
		// 验证手机号与验证码
		Result result = null;
		try {
			 result = commonService.verifyCode(mobile,bizCode,sms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result.getData() == null) {
			return result;
		}
		User user = baseMapper.selectOne(new QueryWrapper< User >().eq("mobile", mobile));
		if (user != null) {
			return Result.fail(CodeMessage.REGISTER_USER_HAS_EXIST);
		}
		user = new User();
		user.setMobile(mobile);
		user.setPassword(Md5Utils.MD5Encode(password));
		//暂时用这个，雪花算法挪了后再改
		String userUniqueCode = UUID.randomUUID().toString().replaceAll("-", "");
		user.setUserUniqueCode(userUniqueCode);
		LoginResp loginResp = new LoginResp();
		try {
			this.baseMapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("用户注册失败，失败原因:{}", e);
			loginResp.setHasBindMobile(false);
			loginResp.setAccessToken("");
			return Result.fail(CodeMessage.REGISTER_FAIL);
		}
		loginResp.setHasBindMobile(true);
		//生成token
		Map<String, Object> map = Maps.newHashMap();
		map.put("userUniqueCode", userUniqueCode);
		String accessToken = JwtTokenUtils.creatJwtToken(secret, JSON.toJSONString(map));
		//把token和对应用户信息储存到redis----token和uniqueode
		CacheUtil.set(UserPrefix.USER_TOKEN,userUniqueCode,accessToken);//redisService.set(accessToken, user, 1L, TimeUnit.HOURS);
		CacheUtil.set(UserPrefix.USER_UNIQUECODE,accessToken,userUniqueCode);
		loginResp.setAccessToken(accessToken);
		return Result.success(loginResp);
	}

	@Override
	public Result modifyPassword(String userUniqueCode, String originPassword, String password) {

		User user = baseMapper.selectOne(new QueryWrapper< User >().eq("user_unique_code", userUniqueCode));
		String md5Password = Md5Utils.MD5Encode(originPassword);
		if (user.getPassword().equals(md5Password)){
			user.setPassword(Md5Utils.MD5Encode(password));
			baseMapper.update(user,new QueryWrapper<User>().eq("user_unique_code",userUniqueCode));
			return Result.success();
		}else if(!md5Password.equals(user.getPassword())){
			return Result.fail(CodeMessage.UPDATE_FAIL_PASSWORD);
		}

		return Result.fail(CodeMessage.UPDATE_FAIL);
	}

    @Override
    public Result bindThird(BindThirdReq bindThirdReq) {
        User user = baseMapper.selectOne(new QueryWrapper< User >().
                eq("user_unique_code", bindThirdReq.getUserUniqueCode()));
		if (user!=null){
			if (UserEnums.bindChannel.QQ_CHANNEL.getValue().equals(bindThirdReq.getBindChannel())){
				if (user.getQqOpenId() != null) {
					return Result.fail(CodeMessage.QQ_UPDATE_ALREADY);
				}
				// 设置qq微信open ID
				user.setQqOpenId(thirdPartyLoginHelper.getQQOpenid(bindThirdReq.getAuthCode(), "qq_bind").getData());
			}else if (UserEnums.bindChannel.WECHAT_CHANNEL.getValue().equals(bindThirdReq.getBindChannel())){
				if (user.getWechatUnionId()== null) {
					return Result.fail(CodeMessage.WECHAT_UPDATE_ALREADY);
				}
				user.setWechatUnionId(thirdPartyLoginHelper.getWechatTokenAndOpenid(bindThirdReq.getAuthCode()).getData().get("openId"));
			}
			baseMapper.update(user,new QueryWrapper<User>().eq("user_unique_code",bindThirdReq.getUserUniqueCode()));

			return Result.success(null) ;
		}
		return Result.fail(CodeMessage.UPDATE_BIND);
    }

    @Override
	public Result unBindThird(UnbindThirdReq unbindThirdReq) {
		User user = baseMapper.selectOne(new QueryWrapper< User >().
				eq("user_unique_code", unbindThirdReq.getUserUniqueCode()));
		if (user!=null){
			if (UserEnums.bindChannel.QQ_CHANNEL.getValue().equals(unbindThirdReq.getBindChannel())){
				user.setQqOpenId(null);
			}else if (UserEnums.bindChannel.WECHAT_CHANNEL.getValue().equals(unbindThirdReq.getBindChannel())){
				user.setWechatUnionId(null);
			}
			baseMapper.update(user,new QueryWrapper<User>().eq("user_unique_code",unbindThirdReq.getUserUniqueCode()));

			return Result.success(null) ;
		}
		return Result.fail(CodeMessage.UPDATE_UNBIND);
	}

	@Override
	public Result loginOut(String userUniqueCode) {
		String accessToken = CacheUtil.get(UserPrefix.USER_TOKEN, userUniqueCode);
		if (StringUtils.isBlank(accessToken))
			return Result.fail(CodeMessage.UPDATE_FAIL);
		if (logInOut(userUniqueCode,accessToken))
			return  Result.success(null);
		return Result.fail(CodeMessage.LOGIN_OUT);
	}

	private  boolean  logInOut(String userUniqueCode,String accessToken ){

		if (StringUtils.isNotBlank(accessToken)){
			//删除USER_TOKEN
			CacheUtil.del(UserPrefix.USER_TOKEN,userUniqueCode);
			//删除USER_UNIQUECODE
			CacheUtil.del(UserPrefix.USER_UNIQUECODE,accessToken);
			return  true;
		}

		return false;


	}

	@Override
	public Result< MessageCountResp > messageCount(String userUniqueCode) {

		String PMNCK = CacheUtil.get(UserPrefix.USER_MESSAGER, CacheKeyEnum.PLATFORM_MSG_NOTIFICATIONS_CACHE_KEY.getKey()+":"+userUniqueCode);
		String PLCK = CacheUtil.get(UserPrefix.USER_MESSAGER, CacheKeyEnum.PERSONAL_LETTER_CACHE_KEY.getKey()+":"+userUniqueCode);

		MessageCountResp messageCountResp = new MessageCountResp();
		messageCountResp.setUnreadNum(Integer.parseInt(PMNCK)+Integer.parseInt(PLCK));
		return Result.success(messageCountResp);
	}

	@Override
	public User getByQQOpenId(String qqOpenId) {
		return this.baseMapper.selectOne(new QueryWrapper<User>().eq("qq_open_id", qqOpenId));
	}

	@Override
	public User getByWeChatOpenId(String weChatOpenId) {
		return this.baseMapper.selectOne(new QueryWrapper<User>().eq("wechat_union_id", weChatOpenId));
	}

	@Override
	public Result<LoginResp> bindMobile(BindMobileReq bindMobileReq) {
		// 验证手机号与验证码
		Result result = null;
		try {
			result = commonService.verifyCode(bindMobileReq.getMobile(),bindMobileReq.getBizCode(),bindMobileReq.getSms());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result.getData() == null) {
			return result;
		}
		// 查看该手机是否已经注册 如果已经注册直接绑定
		User user = baseMapper.selectOne(new QueryWrapper< User >().
				eq("mobile", bindMobileReq.getMobile()));
		LoginResp loginResp = new LoginResp();
		if (user != null){
			BindThirdReq bindThirdReq = new BindThirdReq();
			bindThirdReq.setBindChannel(bindMobileReq.getBindChannel());
			bindThirdReq.setAuthCode(bindMobileReq.getAuthCode());
			bindThirdReq.setUserUniqueCode(user.getUserUniqueCode());
			Result result1 = bindThird(bindThirdReq);
			// 绑定失败
			if (result1.getData() == null) {
				return result1;
			}
		} else {
			user = new User();
			user.setMobile(bindMobileReq.getMobile());
			// 如果是qq
			if (bindMobileReq.getBindChannel() == 1) {
				String qqOpenId = thirdPartyLoginHelper.getQQOpenid(bindMobileReq.getAuthCode(),"qq_bind").getData();
				user.setQqOpenId(qqOpenId);
			}
			// 如果是微信
			if (bindMobileReq.getBindChannel() == 2) {
				String weChatOpenId = thirdPartyLoginHelper.getWechatTokenAndOpenid(bindMobileReq.getAuthCode()).getData().get("openId");
				user.setWechatUnionId(weChatOpenId);
			}
			user.setPassword(Md5Utils.MD5Encode(passwordSalt));
			//暂时用这个，雪花算法挪了后再改
			String userUniqueCode = UUID.randomUUID().toString().replaceAll("-", "");
			user.setUserUniqueCode(userUniqueCode);

			try {
				this.baseMapper.insert(user);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("绑定手机失败，失败原因:{}", e);
				loginResp.setHasBindMobile(false);
				loginResp.setAccessToken("");
				return Result.fail(CodeMessage.BINDMOBILE_FAIL);
			}
		}
		loginResp.setHasBindMobile(true);
		//生成token
		Map<String, Object> map = Maps.newHashMap();
		map.put("userUniqueCode", user.getUserUniqueCode());
		String accessToken = JwtTokenUtils.creatJwtToken(secret, JSON.toJSONString(map));
		//把token和对应用户信息储存到redis
		CacheUtil.set(UserPrefix.USER_TOKEN, user.getUserUniqueCode(),accessToken);//redisService.set(accessToken, user, 1L, TimeUnit.HOURS);
		CacheUtil.set(UserPrefix.USER_UNIQUECODE,accessToken, user.getUserUniqueCode());
		loginResp.setAccessToken(accessToken);
		return Result.success(loginResp);
	}

	/**
	 * 跳转到安全页面（）
	 * @param userUniqueCode
	 * @return
	 */
	@Override
	public Result<AccountSecurityResp> security(String userUniqueCode) {
		User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("user_unique_code", userUniqueCode));
		AccountSecurityResp resp = new AccountSecurityResp();
		resp.setMobile(user.getMobile());
		resp.setQqHasBinded((user.getQqOpenId()==null || user.getQqOpenId() == "")? false:true);
		resp.setWechatHasBinded((user.getWechatUnionId()==null || user.getWechatUnionId() == "")? false:true);
		return Result.success(resp);
	}

	@Override
	public Result changeMobile(ChangeMobileReq changeMobileReq) throws Exception{
		//TODO 更绑手机号流程：1.客户端请求发送短信给旧手机号 2.验证旧手机短信是否正确
		//					3.客户端请求发送短信给新手机 4.验证新手机短信是否正确 5.保存新手机号码到DB
		if (!PhoneNumberUtil.isMobileNO(changeMobileReq.getMobile()) || !PhoneNumberUtil.isMobileNO(changeMobileReq.getOriginMobile()) ){
			return Result.fail(CodeMessage.MOBILE_INCORRECT);
		}
		if (changeMobileReq.getMobile().equals(changeMobileReq.getOriginMobile())) {
			return Result.fail(CodeMessage.MOBILE_SAME);
		}
		//验证验证码是否正确
		// 验证手机号与验证码
		Result result1 = null;
		Result result2 = null;
		try {
			result1 = commonService.verifyCode(changeMobileReq.getOriginMobile(),changeMobileReq.getBizCode(),changeMobileReq.getOriginSms());
			result2 = commonService.verifyCode(changeMobileReq.getMobile(),changeMobileReq.getBizCode(),changeMobileReq.getSms());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 验证失败
		if (result1.getData() == null) {
			return result1;
		}
		if (result2.getData() == null) {
			return result2;
		}
		    User user = baseMapper.selectOne(new QueryWrapper< User >().eq("mobile", changeMobileReq.getOriginMobile()));
		if (user == null) {
			return Result.fail(CodeMessage.LOGIN_USER_NOT_EXIST);
		}
			user.setMobile(changeMobileReq.getMobile());
			try {
				baseMapper.update(user,new QueryWrapper<User>().eq("mobile",changeMobileReq.getOriginMobile()));
				return Result.success();
			} catch (Exception e) {
				e.printStackTrace();
				log.info("换绑手机失败，失败原因:{}", e);
				return Result.fail(CodeMessage.BINDMOBILE_FAIL);
			}

		}

}
