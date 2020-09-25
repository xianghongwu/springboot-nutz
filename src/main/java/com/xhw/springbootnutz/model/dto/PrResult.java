package com.xhw.springbootnutz.model.dto;

import lombok.Data;

@Data
public class PrResult {
    //返回状态码
    private String p_result_code;
    private String p_person_code;
    //人员编号id
    private String p_person_id;
}
