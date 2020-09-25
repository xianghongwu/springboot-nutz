package com.xhw.springbootnutz.exception;

/**
 * 删除异常
 *
 * @author Somer
 * @date 2019-07-19 15:24
 **/
public class DeleteException extends RuntimeException {

    public DeleteException(String message) {
        super(message);
    }
}
