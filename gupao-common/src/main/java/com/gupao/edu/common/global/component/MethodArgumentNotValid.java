//package com.gupao.edu.common.global.component;
//
//import com.gupao.edu.common.result.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import java.util.Arrays;
//import java.util.Set;
//
///**
// * <h3>app-backend</h3>
// * <p>方法校验参数 不通过</p>
// * 校验 抛出了这个异常
// * 使用order(0) 最高优先级顺序
// * 启用日志
// * MethodArgumentNotValidException
// * @author : hduong
// * @version : 1.0
// * @date : 2020-05-02 10:21
// **/
//@Slf4j
//@ControllerAdvice
//@Order(0)
//public class MethodArgumentNotValid {
//
//
//    @ResponseBody
//    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
//    @Order(1)
//    public Result<String> MissingServletRequestParameterExceptionValid2(MissingServletRequestParameterException  exception) {
//        String parmaName = exception.getParameterName();
//        return Result.success(parmaName + "不能为空");
//    }
//
//    @ResponseBody
//    @ExceptionHandler(value = {ConstraintViolationException.class})
//    @Order(1)
//    public Result<String> MissingServletRequestParameterExceptionValid(ConstraintViolationException  exception) {
//        Set<ConstraintViolation<?>> xErrors = exception.getConstraintViolations();
//        for (ConstraintViolation<?> violation : xErrors) {
//            log.warn("valid error: obj[{}], filed[{}], message[{}]", violation.getExecutableParameters(), violation.getInvalidValue(), violation.getMessage());
////            Object[] parmas = violation.getExecutableParameters();
////            String valid = (String) violation.getInvalidValue();
//            String xError = violation.getMessage();
////            return Result.success(Arrays.asList(parmas).toString() + " : " +valid + " : " + xError);
//            return Result.success(xError);
//        }
//        return Result.success(null);
//    }
//
//    /**
//     * 对参数 校验异常做 统一处理
//     * @param exception
//     * @return
//     */
//    @ResponseBody
//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    @Order(0)
//    public Result<String> exceptionHanlder(MethodArgumentNotValidException  exception) {
//        log.error(exception.getMessage(), exception);
////        List<ValidErrorVO> validErrorList = new ArrayList<>();
//        BindingResult result = exception.getBindingResult();
//        for (FieldError fieldError : result.getFieldErrors()) {
////            validErrorList.add(new ValidErrorVO(fieldError.getField(), fieldError.getDefaultMessage()));
//            log.warn("valid error: obj[{}], filed[{}], message[{}]", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
//            String xError = fieldError.getObjectName() + fieldError.getField() + fieldError.getDefaultMessage();
//            return Result.success(xError);
//        }
//        return Result.success(null);
////        ErrorBackVO errorBackVO = new ErrorBackVO(validErrorList);
////        Map<String, ErrorBackVO> map = new HashMap<>();
////        map.put("ErrorBackVO", errorBackVO);
//        //返回错误信息
////        return Result.fail(map);
//    }
//}
