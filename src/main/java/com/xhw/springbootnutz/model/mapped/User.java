package com.xhw.springbootnutz.model.mapped;

import lombok.Data;
import org.nutz.dao.entity.annotation.*;
import org.nutz.json.JsonField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Table("users")
@Data
public class User {

    @Id
    @Column(value = "id", wrap = true)
    private Integer id;

    @Column("username")
    @Comment("用户名")
    private String username;

    @Column("password")
    private String password;

    @Column("height")
    @Comment("身高")
    @ColDefine(width = 15)
    private Double height;

    @Column("register_time")
    @org.nutz.dao.entity.annotation.Comment("注册时间")
    @ColDefine(type = ColType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")//入参格式化
    @JsonField(dataFormat = "yyyy-MM-dd")//出参格式化
    private Date registerTime;
}
