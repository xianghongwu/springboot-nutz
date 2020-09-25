package com.xhw.springbootnutz.controller;

import com.mysql.cj.protocol.x.Notice;
import com.xhw.springbootnutz.model.dto.ajax.AjaxResult;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.util.Daos;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Shinelon
 * @since 2020-09-25 15:56:49
 */
@RestController
@RequestMapping("/nutz")
public class TestNUTZ {
    @Autowired
    Dao dao;

    public AjaxResult test(){
        //一个SqlExpressionGroup就是一个括号   setNot(true)方法是在括号外加   !
        SqlExpressionGroup e1 = Cnd.exps("look_type", "=", 0);
        SqlExpressionGroup e2 = Cnd.exps("look_children", "=", 1);
        Condition cnd_or = Cnd.where(e1).or(e2);


        //过滤contents字段 名称是实体字段名
        /*FieldFilter filter=FieldFilter.locked(Notice.class,"^contents$");
        List<Notice> noticeList = Daos.ext(dao, filter).query(Notice.class, cnd);*/
        return null;
    }
}
