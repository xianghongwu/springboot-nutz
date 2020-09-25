package com.xhw.springbootnutz.service;

import com.xhw.springbootnutz.exception.CustomException;
import com.xhw.springbootnutz.model.mapped.User;
import com.xhw.springbootnutz.util.DateUtils;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestTransactionalService {

    @Autowired
    Dao dao;

    @Transactional
    public void testC(){
        User user=new User();
        user.setUsername("小明");
        user.setPassword("123456");
        user.setHeight(20.50D);
        user.setRegisterTime(DateUtils.getCurrentTime());
        User user2 = dao.insert(user);
        user2.setUsername("氨基酸砥砺奋进傲世狂妃煎熬时空裂缝杰看看拉升砥砺奋进暗示法阿斯顿发士大夫");
        dao.updateIgnoreNull(user2);
        //Double height = user1.getHeight();
    }

    @Transactional
    public void testA(){
        User user=new User();
        user.setUsername("小明");
        user.setPassword("123456");
        user.setHeight(20.50D);
        user.setRegisterTime(DateUtils.getCurrentTime());
        dao.insert(user);

        testB();
    }

    private void testB(){
        User user=new User();
        user.setUsername("小红");
        user.setPassword("654321");
        user.setHeight(250d);
        user.setRegisterTime(DateUtils.getCurrentTime());
        dao.insert(user);
        throw new CustomException("错误");
    }
}
