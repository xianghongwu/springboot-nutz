package com.xhw.springbootnutz.model.mapped;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;


@Table("sys_quick_response_code")
@Data
public class StudentInfoDtoTemp implements Serializable {

    @Column("unionid")
    @Excel(name = "学生唯一标识")
    private String unionId;
    @Column("stuname")
    @Excel(name = "姓名")
    private String stuName;
    @Column("studentid")
    @Excel(name = "学号")
    private String studentId;

}
