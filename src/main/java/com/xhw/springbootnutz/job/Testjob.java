package com.xhw.springbootnutz.job;

import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Testjob {


    @Autowired
    private Dao dao;

    @Autowired
    private Dao secondaryDao;

    //    每天0点过5秒启动
    //@Scheduled(cron = "5 0 0 * * ?")
    //@Scheduled(cron = "0 0/5 * * * ?")
    public void timerToNow(){


    }

}
