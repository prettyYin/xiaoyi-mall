package com.xiaoyi.mall.product.dao;

import com.xiaoyi.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
