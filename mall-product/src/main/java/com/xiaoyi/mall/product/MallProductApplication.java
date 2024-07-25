package com.xiaoyi.mall.product;

import com.xiaoyi.mall.product.dao.AttrGroupDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = AttrGroupDao.class)
@SpringBootApplication
public class MallProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallProductApplication.class,args);
	}
}
