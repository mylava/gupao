package com.gupao.edu.common.global.component;

import com.gupao.edu.common.exception.GuPaoBaseException;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 23/08/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public Result<String> exceptionHanlder(HttpServletRequest request, Exception e) {
		log.error("catch error {}", e);
		if (e instanceof BindException) {
			BindException be = (BindException) e;
			List<ObjectError> errors = be.getAllErrors();
			String[] args = new String[errors.size()];
			for (int i = 0; i < errors.size(); i++) {
				args[i] = errors.get(i).getDefaultMessage();
			}
			return Result.fail(CodeMessage.VALIDATE_ERROR.fillArgs(args));
		} else if (e instanceof FeignException) {
			return Result.fail(CodeMessage.FEIGN_INVOKE_ERROR.fillArgs(((FeignException) e).contentUTF8()));
		} else if(e instanceof MissingServletRequestParameterException){
			String parmaName = ((MissingServletRequestParameterException)e).getParameterName();
			return Result.fail(CodeMessage.PARAM_NAME_NOT_NULL.fillParam(parmaName, true));
		} else if(e instanceof ConstraintViolationException){
			Set<ConstraintViolation<?>> xErrors = ((ConstraintViolationException)e).getConstraintViolations();
			for (ConstraintViolation<?> violation : xErrors) {
				log.warn("valid error: obj[{}], filed[{}], message[{}]", violation.getExecutableParameters(), violation.getInvalidValue(), violation.getMessage());
				String xError = violation.getMessage();
				return Result.fail(CodeMessage.VALID_PARAM_NAME_NOT_NULL.fillParam(xError, false));
			}
			return Result.fail(CodeMessage.SERVER_ERROR);
		} else if(e instanceof MethodArgumentNotValidException){
			log.error(e.getMessage(), e);
			BindingResult result = ((MethodArgumentNotValidException)e).getBindingResult();
			for (FieldError fieldError : result.getFieldErrors()) {
				log.warn("valid error: obj[{}], filed[{}], message[{}]", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
				String xError = fieldError.getObjectName() + fieldError.getField() + fieldError.getDefaultMessage();
				return Result.fail(CodeMessage.VALID_PARAM_NAME_NOT_NULL.fillParam(xError,false));
			}
			return Result.fail(CodeMessage.SERVER_ERROR);
		} else if(e instanceof GuPaoBaseException){
			GuPaoBaseException baseException = (GuPaoBaseException) e;
			return Result.fail(baseException.getCodeMessage());
		}else {
			return Result.fail(CodeMessage.SERVER_ERROR);
		}

	}
}
