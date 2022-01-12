package com.chan.chang.common.exception;

import com.chan.chang.common.entity.Result;
import com.chan.chang.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> error(Exception e) {
        e.printStackTrace();
        return new Result<>(false, StatusCode.ERROR, e.getMessage());
    }
}
