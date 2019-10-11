package com.xhw.springbootnutz.model.dto.ajax;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author:         xhw
 * @CreateDate:     2019-10-11 9:53
 */
public class PageResultData<T> implements Serializable {

    private List<T> row;

    private Integer total;

    public List<T> getRow() {
        return row;
    }

    public void setRow(List<T> row) {
        this.row = row;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
