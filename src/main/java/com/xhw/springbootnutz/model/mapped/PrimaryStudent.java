package com.xhw.springbootnutz.model.mapped;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.nutz.dao.entity.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 普通中小学学生信息
 *
 * @author Somer
 * @date 2018-11-27 16:28
 **/
@Table("bl_primary_student")
@Data
public class PrimaryStudent  implements Serializable {

    @Name
    private String id;

    @Column("JCTB020101")
    @Comment("姓名")
    private String name;

    @Column("JCXS010101")
    @Comment("学号")
    @ColDefine(width = 20)
    private String studentId;

    @Column("pass_type")
    @Comment("出入类型")
    private String passType;

    @Column("JCTB020105")
    @Comment("性别码")
    private String genderCode;

}
