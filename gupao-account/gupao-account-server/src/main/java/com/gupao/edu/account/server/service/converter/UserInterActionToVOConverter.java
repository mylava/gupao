package com.gupao.edu.account.server.service.converter;
import com.google.common.base.Converter;
import com.gupao.edu.account.client.entity.UserInteraction;
import com.gupao.edu.account.client.resp.UserInteractionVO;

import java.time.LocalDate;

/**
 * <h3>gupao-parent</h3>
 * <p>互动消息 转换器 </p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-26 21:58
 **/
public class UserInterActionToVOConverter extends Converter<UserInteraction, UserInteractionVO> {

    private UserInterActionToVOConverter(){}

    public static UserInterActionToVOConverter getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 提供 外部 调用
     * @param userInteraction
     * @return
     */
    public static UserInteractionVO toVO(UserInteraction userInteraction) {
        return getInstance().convert(userInteraction);
    }

    @Override
    protected UserInteractionVO doForward(UserInteraction userInteraction) {
        UserInteractionVO vo = new UserInteractionVO();
        //提醒事件类型 : todo 对应字典表
        vo.setSourceType(String.valueOf(userInteraction.getAttentionTypeId()));
        //提醒标题
        vo.setSubject(userInteraction.getSubject());
        //提醒时间 : 当前时间
        vo.setNoticeTime(LocalDate.now());
        //对方头像
        vo.setOppositeAvatar(userInteraction.getAvatar());
        //对方昵称
        vo.setOppositeName(userInteraction.getNickName());
        //对方唯一编码
        vo.setOppositeUniqueCode(userInteraction.getFromUniqueCode());
        //提醒事件类型 todo 对应字典表
        vo.setSourceType(String.valueOf(userInteraction.getAttentionTypeId()));
        //提醒内容
        vo.setContent(userInteraction.getContent());
        //提醒源ID 内容id
        vo.setResourceId(userInteraction.getResourceId());
        return vo;
    }

    @Override
    protected UserInteraction doBackward(UserInteractionVO userInteractionVO) {
        return null;
    }

    private static class SingletonHolder{
        private static final UserInterActionToVOConverter INSTANCE = new UserInterActionToVOConverter();
    }
}
