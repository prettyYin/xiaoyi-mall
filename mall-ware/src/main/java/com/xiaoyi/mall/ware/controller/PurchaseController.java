package com.xiaoyi.mall.ware.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.xiaoyi.mall.ware.vo.MergeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.xiaoyi.mall.ware.entity.PurchaseEntity;
import com.xiaoyi.mall.ware.service.PurchaseService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.R;



/**
 * 采购信息
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-13 23:29:58
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("ware/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    //@RequiresPermissions("ware:purchase:list")
    public R list(@RequestParam Map<String, Object> params){
        PageInfo page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    //@RequiresPermissions("ware:purchase:info")
    public R info(@PathVariable("id") Long id){
		PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    //@RequiresPermissions("ware:purchase:save")
    public R save(@RequestBody PurchaseEntity purchase){
		purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("ware:purchase:update")
    public R update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    //@RequiresPermissions("ware:purchase:delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 未被合并的采购单列表
     */
    @GetMapping("/unreceive/list")
    public R unreceiveList() {
        PageInfo page = purchaseService.queryPageUnreceiveList();
        return R.ok().put("page", page);
    }

    /**
     * 合并采购需求
     */
    @PostMapping("/merge")
    public R merge(@RequestBody MergeVo mergeVo) {
        purchaseService.mergePurchaseDetail(mergeVo);
        return R.ok();
    }

    /**
     * 领取采购单
     * @param ids 采购单ids
     */
    @PostMapping("/received")
    public R received(@RequestBody List<Long> ids) {
        purchaseService.receivedPurchaseDetail(ids);
        return R.ok();
    }
}
