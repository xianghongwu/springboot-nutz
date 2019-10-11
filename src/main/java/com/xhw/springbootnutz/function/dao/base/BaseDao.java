package com.xhw.springbootnutz.function.dao.base;

import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Somer
 * @date 2018-04-18 14:59
 **/
public class BaseDao {

    @Autowired
    public Dao dao;
}
