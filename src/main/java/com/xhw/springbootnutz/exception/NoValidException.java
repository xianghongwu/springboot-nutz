package com.xhw.springbootnutz.exception;

/**
 * 未验证异常
 *
 * @author Somer
 * @date 2018-09-06 16:37
 **/
public class NoValidException extends RuntimeException {

    public NoValidException(String message) {
        super(message);
    }
}