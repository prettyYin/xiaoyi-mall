package com.xiaoyi.mall.order.dao;

import com.xiaoyi.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-10 23:10:55
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
