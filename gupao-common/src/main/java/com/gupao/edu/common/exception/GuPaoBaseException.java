package com.gupao.edu.common.exception;

import com.gupao.edu.common.result.CodeMessage;
import java.io.Serializable;
import lombok.Getter;

/**
 * @description
 * @author: chenlong
 * @data: 2020-05-04 20:30
 **/
@Getter
public class GuPaoBaseException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -8714512271130663893L;

    private CodeMessage codeMessage;

    public GuPaoBaseException(CodeMessage codeMessage){
        this.codeMessage = codeMessage;
    }
}
