package com.xiaoyi.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.xiaoyi.mall.coupon.entity.HomeAdvEntity;
import com.xiaoyi.mall.coupon.service.HomeAdvService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.R;



/**
 * 首页轮播广告
 *
 * @author chenshun
 * @email 2544212327@qq.com
 * @date 2023-12-13 22:44:38
 */
@RequiredArgsConstructor
@RefreshScope
@RestController
@RequestMapping("coupon/homeadv")
public class HomeAdvController {

    private final HomeAdvService homeAdvService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageInfo page = homeAdvService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		HomeAdvEntity homeAdv = homeAdvService.getById(id);

        return R.ok().put("homeAdv", homeAdv);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HomeAdvEntity homeAdv){
		homeAdvService.save(homeAdv);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody HomeAdvEntity homeAdv){
		homeAdvService.updateById(homeAdv);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		homeAdvService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
