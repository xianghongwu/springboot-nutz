package com.xhw.springbootnutz.exception;

/**
 * 内部错误
 *
 * @author Somer
 * @date 2018-09-14 11:53
 **/
public class InnerException extends RuntimeException {

    public InnerException(String message) {
        super(message);
    }
}