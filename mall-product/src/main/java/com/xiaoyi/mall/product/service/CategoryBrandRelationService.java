package com.xiaoyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageInfo queryPage(Map<String, Object> params);

    /**
     * 修改详细信息
     * @param categoryBrandRelation 分类品牌关联对象
     */
    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

}

