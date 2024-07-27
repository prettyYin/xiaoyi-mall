package com.xiaoyi.mall.product.feign;

import com.xiaoyi.mall.common.utils.R;
import com.xiaoyi.mall.product.to.SaveSkuFullReductionTo;
import com.xiaoyi.mall.product.to.SaveSkuLadderTo;
import com.xiaoyi.mall.product.to.SaveSpuBoundsTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mall-coupon")
public interface CouponFeignService {


    @PostMapping("/coupon/skufullreduction/save")
    R saveSkuFullReduction(@RequestBody SaveSkuFullReductionTo saveTo);

    @PostMapping("/coupon/skuladder/save")
    R saveSkuLadder(SaveSkuLadderTo saveTo);

    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(SaveSpuBoundsTo saveTo);
}
