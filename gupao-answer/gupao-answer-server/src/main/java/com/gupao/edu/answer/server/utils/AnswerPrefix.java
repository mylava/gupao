package com.gupao.edu.answer.server.utils;

import com.gupao.edu.common.cache.key.BasePrefix;

public class AnswerPrefix extends BasePrefix {
    private static final String platName = "Answer";
    public AnswerPrefix(String prefix) {
        super(ONE_DAY, platName+":"+prefix);
    }

}
