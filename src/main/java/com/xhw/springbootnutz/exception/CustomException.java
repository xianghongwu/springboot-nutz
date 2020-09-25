package com.xhw.springbootnutz.exception;

/**
 * 自定义异常
 *
 * @author Somer
 * @date 2019-07-19 15:24
 **/
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
