package com.gupao.edu.account.server.service.converter;/**
 * Created by dudu on 2020/4/26.
 */

import com.google.common.base.Converter;
import com.gupao.edu.account.client.entity.PersonalLetter;
import com.gupao.edu.account.client.entity.SystemAttention;
import com.gupao.edu.account.client.resp.PersonalLetterVO;
import com.gupao.edu.account.client.resp.SystemAttentionVO;

import java.time.LocalDateTime;

/**
 * <h3>gupao-parent</h3>
 * <p>vo 转换器</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-04-26 22:46
 **/
public class SystemAttentionToVOConverter extends Converter<SystemAttention, SystemAttentionVO> {

    private SystemAttentionToVOConverter(){}

    public static SystemAttentionToVOConverter getInstance() {
        return SingletonHolder.INSTANCE;
    }
    /**
     * 提供 外部 调用
     * @param systemAttention
     * @return
     */
    public static SystemAttentionVO toVO(SystemAttention systemAttention) {
        return getInstance().convert(systemAttention);
    }

    @Override
    protected SystemAttentionVO doForward(SystemAttention systemAttention) {
        SystemAttentionVO vo = new SystemAttentionVO();
        vo.setContent(systemAttention.getContent());
        //todo 设置为 当前时间
        vo.setNoticeTime(LocalDateTime.now());
        //内容id
        vo.setSourceId(systemAttention.getSourceId());
        vo.setSubject(systemAttention.getSubject());
        vo.setType(systemAttention.getType());
        return vo;
    }

    @Override
    protected SystemAttention doBackward(SystemAttentionVO systemAttentionVO) {
        return null;
    }

    private static class SingletonHolder{
        private static final SystemAttentionToVOConverter INSTANCE = new SystemAttentionToVOConverter();
    }
}
