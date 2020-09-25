package com.xhw.springbootnutz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootNutzApplicationTests {


	@Autowired
	Dao dao;


	@Test
	public void tes(){
		Sql sql_id = Sqls.create("select max(id) from $table");
		sql_id.vars().set("table","bl_education_bureau_account");
		sql_id.setCallback(Sqls.callback.integer());
		try {
			dao.execute(sql_id);
			int sqlIdInt = sql_id.getInt();
			System.out.println("当前表bl_education_bureau_account的id最大值："+sqlIdInt+"\n");
		}catch (Exception e){
			System.out.println("错误信息："+e.getMessage());
		}
		System.out.println("+++++++++++++++++++++++++");


	}



}
