package com.xiaoyi.mall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.xiaoyi.mall.common.valid.AddGroup;
import com.xiaoyi.mall.common.valid.UpdateGroup;
import com.xiaoyi.mall.common.valid.UpdateStatusGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.xiaoyi.mall.product.entity.BrandEntity;
import com.xiaoyi.mall.product.service.BrandService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.R;


/**
 * 品牌
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageInfo page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@RequestBody @Validated(AddGroup.class) BrandEntity brand){
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@RequestBody @Validated(UpdateGroup.class) BrandEntity brand){
		brandService.updateCascade(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

    @PostMapping("/update/status")
    public R updateStatus(@RequestBody @Validated(UpdateStatusGroup.class) BrandEntity brand) {
        brandService.lambdaUpdate()
                .set(BrandEntity::getShowStatus, brand.getShowStatus())
                .eq(BrandEntity::getBrandId, brand.getBrandId())
                .update();
        return R.ok();
    }
}
