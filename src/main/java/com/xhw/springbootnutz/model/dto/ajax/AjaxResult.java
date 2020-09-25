package com.xhw.springbootnutz.model.dto.ajax;


import lombok.Data;

/**
* @Description:
* @Author:         xhw
* @CreateDate:     2019-10-11 9:53
*/
@Data
public class AjaxResult {

    private Integer code;

    private String message;

    private Object data;

    /**
     * 数据总条数
     */
    private Integer total;

    public AjaxResult() {
    }

    public AjaxResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public AjaxResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AjaxResult(Integer code, String message, Object data,Integer total) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }


}
