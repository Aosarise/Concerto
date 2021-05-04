package com.example.concerto.exception;

import com.example.concerto.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 23:03 2021/4/25
 * @Version: 1.0$
 */
//全局异常处理器
@ControllerAdvice
@Slf4j
public class WebExceptionHandler {

    //处理自定义异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public CommonResponse customerException(CustomException e) {
        e.printStackTrace();
        CommonResponse commonResponse=new CommonResponse(e.getCode(),e.getMessage(),"");
        log.error(e.getMessage());
        return commonResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResponse handle(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        log.error("请求参数错误:" +fieldError.getField() + ":" + fieldError.getDefaultMessage());
        return new CommonResponse(400,"参数错误:" + fieldError.getDefaultMessage(),"");
    }

    //处理自定义异常
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public CommonResponse customerException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        CommonResponse commonResponse=new CommonResponse(400,"json格式错误","");
        log.error(e.getMessage());
        return commonResponse;
    }

    //处理未能捕获（遗漏的）异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse exception(Exception e) {
        e.printStackTrace();
        CommonResponse commonResponse=new CommonResponse(500,"未定义异常 请联系系统管理员","");
        log.error(e.getMessage());
        return commonResponse;
    }
}
