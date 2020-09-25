package com.xhw.springbootnutz.exception;

import com.xhw.springbootnutz.model.dto.ajax.AjaxCode;
import com.xhw.springbootnutz.model.dto.ajax.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.nutz.json.JsonException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

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
     * 处理内部错误
     *
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AjaxResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(AjaxCode.INNER_ERROR, AjaxCode.INNER_ERROR_MESSAGE);
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
        return new AjaxResult(AjaxCode.CONTENT_ERROR, message);
    }

    /**
     * groups参数校验
     *
     * @returne
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new AjaxResult(AjaxCode.CONTENT_ERROR, message);
    }

    /**
     * SQL异常
     *
     * @return
     */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AjaxResult handleSQLException(SQLException e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(AjaxCode.INNER_ERROR, AjaxCode.INNER_ERROR_MESSAGE);
    }

    /**
     * 响应超时
     *
     * @return
     */
    @ExceptionHandler(TimeoutException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public AjaxResult handleTimeoutException(TimeoutException e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(AjaxCode.TIME_OUT_ERROR, AjaxCode.TIME_OUT_ERROR_TEXT);
    }

    /**
     * 非法参数异常
     *
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public AjaxResult handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(AjaxCode.CONTENT_ERROR, AjaxCode.PARAM_ERROR_MESSAGE);
    }

    /**
     * 处理Json格式转换异常
     *
     * @return
     */
    @ExceptionHandler(JsonException.class)
    public AjaxResult handleJsonException() {
        return new AjaxResult(AjaxCode.CONTENT_ERROR, AjaxCode.JSON_EXCEPTION);
    }

    /**
     * 处理自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public AjaxResult handleCustomException(CustomException e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(AjaxCode.CONTENT_ERROR, e.getMessage());
    }


    /**
     * 处理内部错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(InnerException.class)
    public AjaxResult handleInnerException(InnerException e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(AjaxCode.INNER_ERROR, e.getMessage());
    }

    /**
     * 无访问权限
     *
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AjaxResult handleUnauthorizedException() {
        return new AjaxResult(AjaxCode.NO_PERMISSION, AjaxCode.NO_PERMISSION_MESSAGE);
    }

    /**
     * 处理方法级别参数校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public AjaxResult handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(AjaxCode.CONTENT_ERROR, e.getMessage());
    }

    /**
     * 处理参数类型错误异常
     *
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public AjaxResult handleMethodArgumentTypeMismatchException() {
        return new AjaxResult(AjaxCode.CONTENT_ERROR, AjaxCode.PARAM_TYPE_MISMATCH);
    }

    /**
     * 请求方法不存在
     *
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AjaxResult handleNoHandlerFoundException() {
        return new AjaxResult(AjaxCode.NOT_FOUND, AjaxCode.NOT_FOUND_MESSAGE);
    }

    /**
     * 处理请求方法不支持异常
     *
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public AjaxResult handleHttpRequestMethodNotSupportedException() {
        return new AjaxResult(AjaxCode.REQUEST_METHOD_ERROR, AjaxCode.REQUEST_METHOD_ERROR_MESSAGE);
    }

    /**
     * 数值类型不匹配
     *
     * @return
     */
    @ExceptionHandler(NestedServletException.class)
    public AjaxResult nestedServletExceptionHandler() {
        return new AjaxResult(AjaxCode.CONTENT_ERROR, AjaxCode.PARAM_TYPE_MISMATCH);
    }

    /**
     * 处理添加异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AddException.class)
    public AjaxResult handleAddException(AddException e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(AjaxCode.INNER_ERROR, e.getMessage());
    }

    /**
     * 处理更新异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UpdateException.class)
    public AjaxResult handleUpdateException(UpdateException e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(AjaxCode.INNER_ERROR, e.getMessage());
    }

    /**
     * 处理删除异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DeleteException.class)
    public AjaxResult handleDeleteException(DeleteException e) {
        log.error(e.getMessage(), e);
        return new AjaxResult(AjaxCode.INNER_ERROR, e.getMessage());
    }

}