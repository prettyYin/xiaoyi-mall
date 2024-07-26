package com.xiaoyi.mall.product.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoyi.mall.product.entity.CategoryBrandRelationEntity;
import com.xiaoyi.mall.product.service.CategoryBrandRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.product.dao.BrandDao;
import com.xiaoyi.mall.product.entity.BrandEntity;
import com.xiaoyi.mall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;


@Service("brandService")
@RequiredArgsConstructor
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    private final CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<BrandEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (params.containsKey("key") && ObjectUtil.isNotEmpty(params.get("key"))) {
            queryWrapper.like(BrandEntity::getName, params.get("key")).or().like(BrandEntity::getBrandId, params.get("key"));
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );

        return new PageInfo(page);
    }

    @Transactional
    @Override
    public void updateCascade(BrandEntity brand) {
        updateById(brand);
        categoryBrandRelationService
                .lambdaUpdate()
                .eq(CategoryBrandRelationEntity::getBrandId, brand.getBrandId())
                .set(CategoryBrandRelationEntity::getBrandName, brand.getName())
                .update();
    }

}