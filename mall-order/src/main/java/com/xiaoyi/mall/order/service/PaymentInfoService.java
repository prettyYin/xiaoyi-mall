package com.xiaoyi.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.order.entity.PaymentInfoEntity;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-10 23:10:55
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageInfo queryPage(Map<String, Object> params);
}

