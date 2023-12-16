package com.xiaoyi.mall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.xiaoyi.mall.member.feign")
@MapperScan("com.xiaoyi.mall.member.dao")
@SpringBootApplication
public class MallMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallMemberApplication.class,args);
	}
}
