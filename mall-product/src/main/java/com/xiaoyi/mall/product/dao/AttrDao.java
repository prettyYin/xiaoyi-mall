package com.xiaoyi.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoyi.mall.product.entity.AttrEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
	
}
