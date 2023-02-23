package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.req.BindMobileReq;
import com.gupao.edu.account.client.req.BindThirdReq;
import com.gupao.edu.account.client.req.ChangeMobileReq;
import com.gupao.edu.account.client.req.UnbindThirdReq;
import com.gupao.edu.account.client.resp.AccountSecurityResp;
import com.gupao.edu.account.client.resp.LoginResp;
import com.gupao.edu.account.client.resp.MessageCountResp;
import com.gupao.edu.common.result.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserService extends IService<User> {
	User getByMobile(String mobile);

	User getByUserUniqueCode(String userUniqueCode);

	Result<LoginResp> registerUser(String mobile, String password,Integer bizCode,String sms);

	/**
	 * 修改密码
	 * @param userUniqueCode
	 * @param originPassword
	 * @param password
	 * @return
	 */
	Result   modifyPassword(String userUniqueCode,String originPassword,String password );


	/**
	 * 第三方绑定
	 * @param bindThirdReq
	 * @return
	 */
	Result bindThird( BindThirdReq bindThirdReq);
	/**
	 * 解除绑定
	 * @param unbindThirdReq
	 * @return
	 */
	Result   unBindThird(UnbindThirdReq unbindThirdReq);

	/**
	 * 用户退出
	 * @param userUniqueCode
	 * @return
	 */
	Result   loginOut(String userUniqueCode );

	/**
	 * 我的角标
	 * @param userUniqueCode
	 * @return
	 */
	Result< MessageCountResp > messageCount( String userUniqueCode);

	/**
	 *	通过qq授权账号查找账号
	 * @param qqOpenId
	 * @return
	 */
    User getByQQOpenId(String qqOpenId);

	/**
	 * 通过微信授权账号查找账号
	 * @param weChatOpenId
	 * @return
	 */
    User getByWeChatOpenId(String weChatOpenId);

	/**
	 * 通过第三方登录跳转到绑定手机号
	 * @return
	 */
    Result<LoginResp> bindMobile(BindMobileReq bindMobileReq);

	Result<AccountSecurityResp> security(String userUniqueCode);

	/**
	 * 更改绑定手机
	 * @param changeMobileReq
	 */
	Result<Boolean> changeMobile(ChangeMobileReq changeMobileReq) throws Exception;
}
