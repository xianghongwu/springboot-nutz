package com.xhw.springbootnutz;

import org.nutz.dao.util.Daos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.xml.ws.WebEndpoint;

@SpringBootApplication
@EnableTransactionManagement//开启事务
@EnableScheduling//开启定时任务
public class SpringbootNutzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNutzApplication.class, args);
	}

	//开启数据库建表，区分大小写
	@Bean
	public Daos daos(){
		return new Daos() {
			{
				FORCE_WRAP_COLUMN_NAME=true;
			}
		};
	}
}
