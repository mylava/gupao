package com.gupao.edu.account.server.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.gupao.edu.account.client.entity.PersonalLetter;
import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.entity.UserAccount;
import com.gupao.edu.account.client.req.MessageListReq;
import com.gupao.edu.account.client.req.SendLetterReq;
import com.gupao.edu.account.client.req.UserInfoReq;
import com.gupao.edu.account.client.resp.*;
import com.gupao.edu.account.server.enums.MessageNoticeEnum;
import com.gupao.edu.account.server.enums.ReadEnum;
import com.gupao.edu.account.server.service.*;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * comment:
 *
 * @Validated 需要加上 : 否则: @NotBlank不会生效 --> 目前只校验单个参数
 * @author: lipengfei
 * @date: 14/04/2020
 */
@Slf4j
@Api(tags = {"我的页面相关功能"})
@RestController
@RequestMapping("/home")
@Validated
public class UserHomeController {

    /**
     * 平台 互动 消息 服务
     */
    @Autowired
    private UserInteractionService userInteractionService;

    /**
     * 私信 服务
     */
    @Autowired
    private PersonalLetterService personalLetterService;
    /**
     * 平台 通知 服务
     */
    @Autowired
    private SystemAttentionService systemAttentionService;

    /**
     * 用户 详情服务
     */
    @Autowired
    private UserInfoDetailService userInfoDetailService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private MemberService memberService;

    /**
     * 读取平台 通知服务
     */
    @Autowired
    private UserSystemAttentionService userSystemAttentionService;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "1.我的", notes = "我的")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
    @GetMapping(value = "/mainpage")
    public Result<UserHomeResp> mainpage(@RequestParam(value = "userUniqueCode") String userUniqueCode) {
        if (StringUtils.isEmpty(userUniqueCode)) {
            return Result.success();
        }
        UserHomeResp resp = new UserHomeResp();
        //获取消息数量
        resp.setMessageCount(commonService.getMessageNum(userUniqueCode));
        //订单数量
        resp.setOrderCount(commonService.getOrderNum(userUniqueCode));
        //收藏数量
        resp.setFavoriteCount(commonService.getFavoriteNum(userUniqueCode));
        // 优惠券数量
        resp.setCouponCount(commonService.getCouponNum(userUniqueCode));
        //查询用户信息
        User user = userService.getByUserUniqueCode(userUniqueCode);
        if (user != null) {
            //头像
            resp.setAvatar(user.getAvatar());
            //昵称
            resp.setNickName(user.getNickName());
        }
        //查询账户信息
        UserAccount userAccount = userAccountService.finByUserUniqueCode(userUniqueCode);
        if (userAccount != null) {
            //可用学币数量
            resp.setAccountBalance(userAccount.getAvailableAmount());
        }
        //查询是是否有学籍,查询是否会员、剩余天数、书籍领取状态
        resp = memberService.setMemberInfo(resp, userUniqueCode);
        //todo 查询是否有学籍
        return Result.success(resp);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "2.我的主页", notes = "我的主页")
    @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String")
    @PostMapping(value = "/homepage")
    public Result<UserHomepageResp> homepage(@RequestParam(value = "userUniqueCode") String userUniqueCode) {
        //TODO 1.查询头像、昵称信息、是否有学籍 2.从redis获取消息数量、订单数量、可用学币数量、收藏数量、优惠券数量
        // 		3.查询是否会员、剩余天数、书籍领取状态
        return Result.success();
    }


    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "3.查看个人资料", notes = "查看个人资料")
    @ApiImplicitParam(name = "targetUniqueCode", value = "用户唯一编码", dataType = "String")
    @GetMapping(value = "/userInfo")
    public Result<UserInfoResp> userInfo(@NotBlank(message = "用户唯一编码不能为空") @RequestParam(value = "targetUniqueCode") String targetUniqueCode) {
        // 查询用户个人资料
        log.info("查看个人资料, 请求参数:{}", targetUniqueCode);
        UserInfoResp userInfoResp = userInfoDetailService.getUserInfo(targetUniqueCode);
        log.info("查看个人资料, 响应结果:{}", userInfoResp);
        return Result.success(userInfoResp);
    }

    /**
     * 服务内部的互相调用直接返 传输的对象
     * @param userUniqueCode
     * @return
     */
    @GetMapping("/getUserSimpleInfo")
    public UserSimpleInfoDTO getUserSimpleInfo(@RequestParam("userUniqueCode") String userUniqueCode) {
        User user = userService.getByUserUniqueCode(userUniqueCode);
        UserSimpleInfoDTO userSimpleInfoDTO = new UserSimpleInfoDTO();
        userSimpleInfoDTO.setAvatar(user.getAvatar());
        userSimpleInfoDTO.setNickName(user.getNickName());
        userSimpleInfoDTO.setUserUniqueCode(userUniqueCode);
        return userSimpleInfoDTO;
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "4.修改个人资料", notes = "修改个人资料")
    @ApiImplicitParam(name = "userInfoReq", value = "个人资料实体", dataType = "UserInfoReq")
    @PostMapping(value = "/modifyUserInfo")
    public Result modifyUserInfo(@Valid @RequestBody UserInfoReq userInfoReq) {
        // 保存用户个人资料
        log.info("修改个人资料, 请求参数:{}", userInfoReq);
        boolean flag = userInfoDetailService.update(userInfoReq);
        if (flag) {
            log.info("修改个人资料, 响应结果是否成功:{}", flag);
            return Result.success();
        }
        return Result.fail(CodeMessage.UPDATE_USER_FAIL);
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "5.我的消息列表", notes = "我的消息列表")
    @ApiImplicitParam(name = "messageListReq", value = "我的消息列表请求参数", required = true, dataType = "MessageListReq")
    @PostMapping(value = "/messageList")
    public Result<MessageResp> messageList(@Valid @RequestBody MessageListReq messageListReq) {
        log.info("查询消息列表,请求参数: {}", messageListReq);
        //  1.分别查询平台通知、互动消息、私信的数量（是否放入缓存）
        //  2.创建互动消息相关枚举类
        //  3.根据类型查询具体消息列表
        MessageResp messageResp = new MessageResp();
        // 平台通知消息类型 不用做了 待确认删除 todo
        if (MessageNoticeEnum.PLATFORM_MSG_NOTIFICATIONS.getMessageType() == messageListReq.getMessageType()) {
            messageResp.setSystemAttentionList(systemAttentionService.listAttentionPage(messageListReq));
            messageResp.setSystemCount(systemAttentionService.count(messageListReq));
        }
        //互动消息类型
        //私信消息类型
        if (MessageNoticeEnum.PERSONAL_LETTER.getMessageType() == messageListReq.getMessageType()) {
            //直接用list
            messageResp.setPersonalLetterVOList(personalLetterService.listPage(messageListReq));
            messageResp.setLetterCount(personalLetterService.count(messageListReq));
        }
        log.info("查询消息列表,响应结果: {}", messageResp);
        return Result.success(messageResp);
    }

    /**
     * 通知详情 ： 是不是不要这么多参数
     * 是前端来根据类型跳转详情页面的吧?
     *
     * @param userUniqueCode
     * @param type
     * @param sourceId
     * @param id
     * @return
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "6.平台通知详情", notes = "平台通知详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "通知类型", dataType = "Integer"),
            @ApiImplicitParam(name = "sourceId", value = "资源ID", dataType = "Integer"),
            @ApiImplicitParam(name = "id", value = "平台通知id", dataType = "Integer")
    })
    @PostMapping(value = "/systemNotice")
    public Result<SystemAttentionVO> systemNotice(@NotBlank(message = "用户唯一编码不能为空") @RequestParam(value = "userUniqueCode") String userUniqueCode,
                                                  @RequestParam(value = "type") Integer type,
                                                  @RequestParam(value = "sourceId") Integer sourceId,
                                                  @NotNull(message = "平台通知id不能为空") @RequestParam(value = "id") Integer id) {
        // 1.根据通知类型，查找相关系统通知详情
        // todo 这个 通知详情 应该就是这个 通知表的内容的 然后 前端点击的时候 会根据资源id和类型去跳转对应的页面
        log.info("平台通知详情,请求参数:用户唯一编码: {}; 通知类型 : {};资源ID : {}; 平台通知id: {}", userUniqueCode, type, sourceId, id);
        SystemAttentionVO vo = systemAttentionService.getSystemAttentionDetail(id);
        log.info("查询消息列表,响应结果: {}", vo);
        //添加更新 平台通知 阅读 todo 改为发消息异步通知 更新
        userSystemAttentionService.readSystemAttention(userUniqueCode, id, ReadEnum.MARK_READ.getReadStatus());
        return Result.success(vo);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "7.私信详情", notes = "私信详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", dataType = "String"),
            @ApiImplicitParam(name = "oppositeUniqueCode", value = "对方唯一编码", dataType = "String")
    })
    @PostMapping(value = "/personalLetter")
    public Result<List<PersonalLetterDetailVO>> userInteraction(@RequestParam(value = "userUniqueCode") String userUniqueCode,
                                                                @RequestParam(value = "oppositeUniqueCode") String oppositeUniqueCode) {
        // 1.根据用户编码 和 对方编码 查询所有私信内容 按时间升序排列成集合
        log.info("查询所有私信内容,请求参数:发送方用户唯一编码:{}; 接收方用户唯一编码: {}", oppositeUniqueCode, userUniqueCode);
        List<PersonalLetterDetailVO> resultVos = personalLetterService.listPersonalLetterDetail(userUniqueCode, oppositeUniqueCode);
        log.info("查询所有私信内容,响应结果: {}", resultVos);
        //添加更新 私信 阅读 todo 改为发消息异步通知 更新
        SendLetterReq sendLetterReq = new SendLetterReq();
        sendLetterReq.setOppositeUniqueCode(oppositeUniqueCode);
        sendLetterReq.setUserUniqueCode(userUniqueCode);
        personalLetterService.batchUpdate(sendLetterReq);
        return Result.success(resultVos);
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "8.发送私信", notes = "发送私信")
    @ApiImplicitParam(name = "sendLetterReq", value = "发送私信请求参数", required = true, dataType = "SendLetterReq")
    @PostMapping(value = "/sendLetter")
    public Result<List<PersonalLetterDetailVO>> sendLetter(@Valid @RequestBody SendLetterReq sendLetterReq) {
        log.info("发送私信,请求参数: {}", sendLetterReq);
        // 1.保存私信到DB 2.查询最近私信记录并返回
        PersonalLetter personalLetter = new PersonalLetter();
        if (sendLetterReq.getIsFirstNews()) {
            //设置 0 标识 第一条消息 : 这个parent_id 可以不要, 根据创建时间查询 按顺序展示就可以l
            personalLetter.setParentId(0);
        } else {
            //设置默认值
            personalLetter.setParentId(1);
        }
        personalLetter.setCreateTime(LocalDateTime.now());
        personalLetter.setFromUniqueCode(sendLetterReq.getOppositeUniqueCode());
        personalLetter.setMessage(sendLetterReq.getMessage());
        personalLetter.setTargetUniqueCode(sendLetterReq.getUserUniqueCode());
        //是否 为图片传过来做什么 todo 先校验用户是否存在: 不存在就不发了
        Map<String, Object> parMap = new HashMap<>(4);
        parMap.put("user_unique_code", sendLetterReq.getUserUniqueCode());
        int size = userService.getBaseMapper().selectByMap(parMap).size();
        if (size == 0) {
            return Result.fail(CodeMessage.SENDER_LETTER_USER_NOT_EXIST);
        }
        parMap.put("user_unique_code", sendLetterReq.getOppositeUniqueCode());
        int size2 = userService.getBaseMapper().selectByMap(parMap).size();
        if (size2 == 0) {
            return Result.fail(CodeMessage.RECEIVEDER_LETTER_USER_NOT_EXIST);
        }
        boolean result = personalLetterService.save(personalLetter);
        log.info("发送私信,响应结果: {}", sendLetterReq);
        if (result) {
            List<PersonalLetterDetailVO> vos = personalLetterService.listPersonalLetterDetail(sendLetterReq.getUserUniqueCode(),
                    sendLetterReq.getOppositeUniqueCode());
            return Result.success(vos);
        }
        log.error("发送私信失败: 结果: {}", result);
        return Result.fail(null);
    }
    //todo 添加 feign的 服务提供者方法
    //****************新增 阅读 私信
    //****************新增 阅读 平台通知
//    @ApiOperationSupport(order = 9)
//    @ApiOperation(value = "9.阅读私信", notes = "阅读私信")
//    @ApiImplicitParam(name = "readLetter", value = "发送私信请求参数", required = true, dataType = "SendLetterReq")
//    @PostMapping(value = "/readLetter")
//    public Result<Boolean> readPersonalLetter(@RequestBody SendLetterReq readLetter) {
//        return Result.success(personalLetterService.update(readLetter) > 0);
//    }
//
//    @ApiOperationSupport(order = 10)
//    @ApiOperation(value = "10.阅读平台通知", notes = "阅读平台通知")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userUniqueCode", value = "用户唯一编码", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "systemAttentionId", value = "平台通知id", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "readStatus", value = "是否已读 0:未读 1:已读", required = true, dataType = "Integer")
//    })
//    @PostMapping(value = "/readSystemAttention")
//    public Result<Boolean> readPersonalLetter(@RequestParam(value = "userUniqueCode") String userUniqueCode,
//                                              @RequestParam(value = "systemAttentionId") Integer systemAttentionId,
//                                              @RequestParam(value = "readStatus")int readStatus) {
//        return Result.success(userSystemAttentionService.readSystemAttention(userUniqueCode, systemAttentionId, readStatus) > 0);
//    }

}
