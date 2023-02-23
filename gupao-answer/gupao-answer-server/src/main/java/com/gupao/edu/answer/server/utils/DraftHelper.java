package com.gupao.edu.answer.server.utils;


import com.gupao.edu.answer.client.enumeration.ReferType;
import com.gupao.edu.common.cache.CacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DraftHelper {


    public boolean verity(Integer userId, ReferType referType){
        if(userId==null || referType == null){
            return false;
        }
        String code = (String) CacheUtil.get(new AnswerPrefix("Draft:"),userId+referType.getCode());
        if(StringUtils.isBlank(code)){
            return true;
        }
        return false;
    }


}