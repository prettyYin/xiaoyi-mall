package com.xiaoyi.mall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xiaoyi.mall.ware.dao")
@SpringBootApplication
public class MallWareApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallWareApplication.class,args);
	}
}
