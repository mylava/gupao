package com.gupao.edu.course.server.exception;

import com.gupao.edu.common.exception.GuPaoBaseException;
import com.gupao.edu.common.result.CodeMessage;

/**
 * @description
 * @author: chenlong
 * @data: 2020-05-04 20:34
 **/
public class CourseException extends GuPaoBaseException {

    public CourseException(CodeMessage codeMessage) {
        super(codeMessage);
    }
}
