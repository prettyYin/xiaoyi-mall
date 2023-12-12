package com.xiaoyi.mall.product;


import com.xiaoyi.mall.product.entity.BrandEntity;
import com.xiaoyi.mall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallProductionApplicationTests {

	@Autowired
	BrandService brandService;

	@Test
	public void contextTest() {
		BrandEntity brand = new BrandEntity();
		brand.setName("Apple");
		brandService.save(brand);
		System.out.println("插入成功！");
	}
}
