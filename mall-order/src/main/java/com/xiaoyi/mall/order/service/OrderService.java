package com.xiaoyi.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-10 23:10:55
 */
public interface OrderService extends IService<OrderEntity> {

    PageInfo queryPage(Map<String, Object> params);
}

