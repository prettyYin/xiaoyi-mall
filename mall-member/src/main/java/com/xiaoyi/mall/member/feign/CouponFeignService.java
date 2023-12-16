package com.xiaoyi.mall.member.feign;

import com.xiaoyi.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "remoteCouponClientService")
public interface CouponFeignService {

	@GetMapping("/coupon/info/{id}")
	public R getById(@PathVariable("id") Long id);
}
