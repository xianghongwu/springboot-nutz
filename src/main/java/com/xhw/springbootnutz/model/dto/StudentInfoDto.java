package com.xhw.springbootnutz.model.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class StudentInfoDto implements Serializable {
    @Excel(name = "unionId")
    private String unionId;
    @Excel(name = "学生姓名")
    private String stuName;
    @Excel(name = "学号")
    private String studentId;
}
