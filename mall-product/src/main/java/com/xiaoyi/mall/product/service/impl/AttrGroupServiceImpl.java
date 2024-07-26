package com.xiaoyi.mall.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoyi.mall.product.entity.AttrEntity;
import com.xiaoyi.mall.product.service.AttrService;
import com.xiaoyi.mall.product.service.CategoryService;
import com.xiaoyi.mall.product.vo.AttrGroupWithAttrsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.product.dao.AttrGroupDao;
import com.xiaoyi.mall.product.entity.AttrGroupEntity;
import com.xiaoyi.mall.product.service.AttrGroupService;


@Service("attrGroupService")
@RequiredArgsConstructor
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    private final CategoryService categoryService;
    private final AttrService attrService;

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageInfo(page);
    }

    @Override
    public PageInfo queryBaseAttrPage(Map<String, Object> params, String categoryId) {
        // categoryId == 0,查询所有，否则查出相关分类的商品属性信息
        LambdaQueryWrapper<AttrGroupEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (!StrUtil.equals(categoryId, "0")) {
            queryWrapper.eq(AttrGroupEntity::getCatelogId, categoryId);
        }
        // 根据key查询条件
        if (params.containsKey("key") && StrUtil.isNotEmpty(params.get("key").toString())) {
            queryWrapper
                    .like(AttrGroupEntity::getAttrGroupId, params.get("key"))
                    .or()
                    .like(AttrGroupEntity::getAttrGroupName, params.get("key"));
        }
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                queryWrapper
        );

        return new PageInfo(page);
    }

    @Override
    public AttrGroupEntity selectById(Long attrGroupId) {
        AttrGroupEntity attrGroup = getById(attrGroupId);
        List<Long> catelogPath = new ArrayList<>();
        if (ObjectUtil.isNull(attrGroup) && ObjectUtil.isNull(attrGroup.getCatelogId())) {
            return null;
        }
        // 根据当前的分类id查询出完整的父级分类id列表
        Long catelogId = attrGroup.getCatelogId();
        while (ObjectUtil.isNotNull(catelogId) && catelogId.compareTo(0L) != 0) { // =0 代表为根级分类
            catelogPath.add(catelogId);
            catelogId = categoryService.getById(catelogId).getParentCid();
        }
        ListUtil.reverse(catelogPath);
        attrGroup.setCatelogPath(catelogPath);
        return attrGroup;
    }

    /**
     * 根据分类id查出所有的分组以及这些组里面的属性
     * @param catelogId
     * @return
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        //com.atguigu.gulimall.product.vo
        //1、查询分组信息
        List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));

        //2、查询所有属性
        List<AttrGroupWithAttrsVo> collect = attrGroupEntities.stream().map(group -> {
            AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group,attrsVo);
            List<AttrEntity> attrs = attrService.getRelationAttr(attrsVo.getAttrGroupId());
            attrs = CollectionUtil.isEmpty(attrs) ? ListUtil.empty() : attrs;
            attrsVo.setAttrs(attrs);
            return attrsVo;
        }).collect(Collectors.toList());

        return collect;


    }

}