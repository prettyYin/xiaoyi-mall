package com.xiaoyi.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.ware.entity.PurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-13 23:29:58
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageInfo queryPage(Map<String, Object> params);
}

