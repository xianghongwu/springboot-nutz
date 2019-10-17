package com.xhw.springbootnutz.exception;

import com.xhw.springbootnutz.model.dto.ajax.AjaxResult;
import com.xhw.springbootnutz.model.dto.ajax.ResultState;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 自定义API异常处理
 *
 * @author Somer
 * @date 2018-04-18 19:52
 **/
@RestControllerAdvice
public class RRExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理方法级别参数校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public AjaxResult handleConstraintViolationException(ConstraintViolationException e) {
        return new AjaxResult(ResultState.CONTENT_ERROR, e.getMessage());
    }

    /**
     * 验证信息异常处理
     *
     * @return
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult bindExceptionHandler(BindException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        StringBuffer sb = new StringBuffer();
        for (FieldError error : errors) {
            sb.append(error.getField() + "：" + error.getDefaultMessage() + "；");
        }
        String message = sb.substring(0, sb.lastIndexOf("；"));
        return new AjaxResult(ResultState.CONTENT_ERROR, message);
    }


    /**
     * 数值类型不匹配
     *
     * @return
     */
    @ExceptionHandler(NestedServletException.class)
    public AjaxResult nestedServletExceptionHandler() {
        return new AjaxResult(ResultState.FAIL, ResultState.TYPE_MISMATCH);
    }

    /**
     * 权限异常
     *
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public AjaxResult handleUnauthorizedException() {
        try {

        } catch (UnauthorizedException e) {
            logger.error(e.getMessage(), e);
        }
        return new AjaxResult(ResultState.PERM_ERROR, ResultState.NO_PERMISSION);
    }

}
