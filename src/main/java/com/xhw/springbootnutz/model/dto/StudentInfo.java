package com.xhw.springbootnutz.model.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class StudentInfo  implements Serializable {
    @Excel(name = "学生名称", orderNum = "0")
    private String stuName;
    @Excel(name = "学生学号", orderNum = "1")
    private String stuNo;
    @Excel(name = "学生出入类型", orderNum = "2")
    private String stuType;
    @Excel(name = "性别", orderNum = "3")
    private String stuSex;

    @Override
    public String toString() {
        return "StudentInfo{" +
                "stuName='" + stuName + '\'' +
                ", stuNo='" + stuNo + '\'' +
                ", stuType='" + stuType + '\'' +
                ", stuSex='" + stuSex + '\'' +
                '}';
    }
}
