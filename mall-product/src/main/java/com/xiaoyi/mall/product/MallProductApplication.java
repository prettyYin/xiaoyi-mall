package com.xiaoyi.mall.product;

import com.xiaoyi.mall.product.dao.AttrGroupDao;
import com.xiaoyi.mall.product.feign.CouponFeignService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableDiscoveryClient
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@EnableFeignClients(basePackageClasses = CouponFeignService.class)
@MapperScan(basePackageClasses = AttrGroupDao.class)
@SpringBootApplication
public class MallProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallProductApplication.class,args);
	}
}
