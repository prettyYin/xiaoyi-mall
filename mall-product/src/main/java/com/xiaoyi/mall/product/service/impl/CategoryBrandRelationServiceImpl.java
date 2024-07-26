package com.xiaoyi.mall.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoyi.mall.product.dao.BrandDao;
import com.xiaoyi.mall.product.dao.CategoryDao;
import com.xiaoyi.mall.product.entity.BrandEntity;
import com.xiaoyi.mall.product.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.product.dao.CategoryBrandRelationDao;
import com.xiaoyi.mall.product.entity.CategoryBrandRelationEntity;
import com.xiaoyi.mall.product.service.CategoryBrandRelationService;

import javax.annotation.Resource;


@Service("categoryBrandRelationService")
@RequiredArgsConstructor
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Lazy
    @Resource
    private BrandService brandService;
    private final BrandDao brandDao;
    private final CategoryDao categoryDao;
    private final CategoryBrandRelationDao relationDao;

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<CategoryBrandRelationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(params.containsKey("brandId") && ObjectUtil.isNotEmpty(params.get("brandId")),
                CategoryBrandRelationEntity::getBrandId, params.get("brandId"));
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                queryWrapper
        );

        return new PageInfo(page);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        String brandName = brandDao.selectById(categoryBrandRelation.getBrandId()).getName();
        String catelogName = categoryDao.selectById(categoryBrandRelation.getCatelogId()).getName();
        categoryBrandRelation.setBrandName(brandName);
        categoryBrandRelation.setCatelogName(catelogName);
        save(categoryBrandRelation);
    }

    public List<BrandEntity> getBrandsByCatId(Long catId) {
        List<CategoryBrandRelationEntity> relations = relationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
        List<Long> brandIds = relations.stream().map(CategoryBrandRelationEntity::getBrandId).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(brandIds)) {
            return ListUtil.empty();
        }
        return brandService.listByIds(brandIds);
    }

}