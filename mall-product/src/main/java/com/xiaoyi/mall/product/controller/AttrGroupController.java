package com.xiaoyi.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.xiaoyi.mall.product.entity.AttrEntity;
import com.xiaoyi.mall.product.service.AttrAttrgroupRelationService;
import com.xiaoyi.mall.product.service.AttrService;
import com.xiaoyi.mall.product.vo.AttrGroupRelationVo;
import com.xiaoyi.mall.product.vo.AttrGroupWithAttrsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.xiaoyi.mall.product.entity.AttrGroupEntity;
import com.xiaoyi.mall.product.service.AttrGroupService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.R;



/**
 * 属性分组
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("product/attrgroup")
public class AttrGroupController {

    private final AttrService attrService;
    private final AttrGroupService attrGroupService;
    private final AttrAttrgroupRelationService relationService;

    /**
     * 列表
     */
    @GetMapping("/list/{categoryId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("categoryId")String categoryId){
        PageInfo page = attrGroupService.queryBaseAttrPage(params,categoryId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.selectById(attrGroupId);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 属性分组的关联信息
     */
    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> relationList = attrService.getRelationAttr(attrgroupId);
        return R.ok(relationList);
    }

    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId")Long catelogId){
        //1、查出当前分类下的所有属性分组，
        //2、查出每个属性分组的所有属性
        List<AttrGroupWithAttrsVo> vos =  attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data",vos);
    }

    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params){
        PageInfo page = attrService.getNoRelationAttr(params,attrgroupId);
        return R.ok().put("page",page);
    }

    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody  AttrGroupRelationVo[] vos){
        attrService.deleteRelation(vos);
        return R.ok();
    }

    @PostMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos){

        relationService.saveBatch(vos);
        return R.ok();
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));
        return R.ok();
    }

}
