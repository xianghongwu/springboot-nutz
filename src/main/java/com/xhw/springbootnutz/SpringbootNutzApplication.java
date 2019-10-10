package com.xhw.springbootnutz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//开启事务
@EnableScheduling//开启定时任务
public class SpringbootNutzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNutzApplication.class, args);
	}

}
