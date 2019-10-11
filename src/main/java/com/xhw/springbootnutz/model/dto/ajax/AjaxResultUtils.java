package com.xhw.springbootnutz.model.dto.ajax;

/**
 * @Description:
 * @Author:         xhw
 * @CreateDate:     2019-10-11 9:53
 */
public class AjaxResultUtils {

    public static <T> AjaxResult resultMessage(String message) {
        return new AjaxResult(ResultState.CONTENT_ERROR, ResultState.CONTENT_ERROR_MESSAGE, message);
    }

    public static <T> AjaxResult addMessage(T t) {
        if (null != t) {
            return new AjaxResult(ResultState.OK, ResultState.ADD_SUCCESS, t);
        } else {
            return new AjaxResult(ResultState.INNER_ERROR, ResultState.INNER_ERROR_MESSAGE, t);
        }
    }

    public static <T> AjaxResult updateMessage(T t) {
        if (null != t) {
            return new AjaxResult(ResultState.OK, ResultState.UPDATE_SUCCESS, t);
        } else {
            return new AjaxResult(ResultState.INNER_ERROR, ResultState.INNER_ERROR_MESSAGE, t);
        }
    }

    public static <T> AjaxResult getInfoMessage(T t) {
        if (null != t) {
            return new AjaxResult(ResultState.OK, ResultState.GET_SUCCESS, t);
        } else {
            return new AjaxResult(ResultState.CONTENT_ERROR, ResultState.NO_DATA);
        }
    }

    public static AjaxResult delInfoMessage(boolean flag) {
        if (flag) {
            return new AjaxResult(ResultState.OK, ResultState.DELETE_SUCCESS);
        } else {
            return new AjaxResult(ResultState.CONTENT_ERROR, ResultState.CONTENT_ERROR_MESSAGE);
        }
    }
}
