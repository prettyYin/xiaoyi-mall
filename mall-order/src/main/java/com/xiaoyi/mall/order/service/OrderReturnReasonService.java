package com.xiaoyi.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-10 23:10:55
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageInfo queryPage(Map<String, Object> params);
}

