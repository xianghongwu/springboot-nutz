package com.xhw.springbootnutz.exception;

import org.apache.shiro.ShiroException;

/**
 * 未认证异常
 *
 * @author Somer
 * @date 2018-08-29 9:02
 **/
public class NoAuthException extends ShiroException {

    public NoAuthException(String message) {
        super(message);
    }
}