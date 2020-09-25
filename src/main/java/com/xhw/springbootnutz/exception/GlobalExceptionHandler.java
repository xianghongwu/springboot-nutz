package com.xhw.springbootnutz.exception;

import com.xhw.springbootnutz.model.dto.ajax.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.nutz.json.JsonException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 全局异常处理
 *
 * @author Somer
 * @date 2018-08-20 16:02
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public AjaxResult handleCustomException(CustomException e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(201, e.getMessage());
    }

}