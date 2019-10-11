package com.xhw.springbootnutz.model.dto.ajax;

import java.io.Serializable;

/**
 * 总数统计
 *
 * @author Somer
 * @create 2018-01-03 11:08
 **/
public class TotalCount implements Serializable {

    private Integer totalNumber;

    private Double totalCount;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Double totalCount) {
        this.totalCount = totalCount;
    }
}
