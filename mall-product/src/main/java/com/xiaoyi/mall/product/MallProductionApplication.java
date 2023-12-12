package com.xiaoyi.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xiaoyi.mall.product.dao")
@SpringBootApplication
public class MallProductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallProductionApplication.class,args);
	}
}
