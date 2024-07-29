package com.xiaoyi.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.ware.entity.PurchaseDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-13 23:29:57
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageInfo queryPage(Map<String, Object> params);

    void updateReceivingByPurchaseIds(List<Long> purchaseIds);
}

