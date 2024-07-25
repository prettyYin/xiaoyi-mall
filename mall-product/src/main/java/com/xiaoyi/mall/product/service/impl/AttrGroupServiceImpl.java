package com.xiaoyi.mall.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoyi.mall.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

}