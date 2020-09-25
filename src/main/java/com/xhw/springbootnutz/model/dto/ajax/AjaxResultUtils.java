package com.xhw.springbootnutz.model.dto.ajax;

import com.xhw.springbootnutz.exception.AddException;
import com.xhw.springbootnutz.exception.UpdateException;

/**
 * @Description:
 * @Author:         xhw
 * @CreateDate:     2019-10-11 9:53
 */
public class AjaxResultUtils {

    public static <T> AjaxResult resultMessage(String message) {
        return new AjaxResult(AjaxCode.CONTENT_ERROR, AjaxCode.CONTENT_ERROR_MESSAGE, message);
    }

    public static <T> AjaxResult addMessage(T t) {
        if (null != t) {
            return new AjaxResult(AjaxCode.SUCCESS, AjaxCode.ADD_SUCCESS, t);
        } else {
            throw new AddException(AjaxCode.ADD_EXCEPTION);
        }
    }

    public static <T> AjaxResult updateMessage(T t) {
        if (null != t) {
            return new AjaxResult(AjaxCode.SUCCESS, AjaxCode.UPDATE_SUCCESS, t);
        } else {
            throw new UpdateException(AjaxCode.UPDATE_EXCEPTION);
        }
    }

    public static <T> AjaxResult getInfoMessage(T t) {
        if (null != t) {
            return new AjaxResult(AjaxCode.SUCCESS, AjaxCode.GET_SUCCESS, t);
        } else {
            return new AjaxResult(AjaxCode.CONTENT_ERROR, AjaxCode.NO_DATA);
        }
    }

    public static <T> AjaxResult getInfoMessage(T t,Integer total) {
        if (null != t) {
            return new AjaxResult(AjaxCode.SUCCESS, AjaxCode.GET_SUCCESS, t,total);
        } else {
            return new AjaxResult(AjaxCode.CONTENT_ERROR, AjaxCode.NO_DATA);
        }
    }

    public static AjaxResult delInfoMessage(boolean flag) {
        if (flag) {
            return new AjaxResult(AjaxCode.SUCCESS, AjaxCode.DELETE_SUCCESS);
        } else {
            throw new AddException(AjaxCode.DELETE_EXCEPTION);
        }
    }
}
