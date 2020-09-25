package com.xhw.springbootnutz.exception;

/**
 * 更新异常
 *
 * @author Somer
 * @date 2019-07-19 15:24
 **/
public class UpdateException extends RuntimeException {

    public UpdateException(String message) {
        super(message);
    }
}
