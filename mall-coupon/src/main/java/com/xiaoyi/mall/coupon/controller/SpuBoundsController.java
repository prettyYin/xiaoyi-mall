package com.xiaoyi.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import com.xiaoyi.mall.coupon.to.SaveSpuBoundsTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyi.mall.coupon.entity.SpuBoundsEntity;
import com.xiaoyi.mall.coupon.service.SpuBoundsService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.R;



/**
 * 商品spu积分设置
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-13 23:17:43
 */
@RestController
@RequestMapping("coupon/spubounds")
public class SpuBoundsController {
    @Autowired
    private SpuBoundsService spuBoundsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:spubounds:list")
    public R list(@RequestParam Map<String, Object> params){
        PageInfo page = spuBoundsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:spubounds:info")
    public R info(@PathVariable("id") Long id){
		SpuBoundsEntity spuBounds = spuBoundsService.getById(id);

        return R.ok().put("spuBounds", spuBounds);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:spubounds:save")
    public R save(@RequestBody SaveSpuBoundsTo saveTo){
        SpuBoundsEntity spuBounds = BeanUtil.copyProperties(saveTo, SpuBoundsEntity.class);
		spuBoundsService.save(spuBounds);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:spubounds:update")
    public R update(@RequestBody SpuBoundsEntity spuBounds){
		spuBoundsService.updateById(spuBounds);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:spubounds:delete")
    public R delete(@RequestBody Long[] ids){
		spuBoundsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
