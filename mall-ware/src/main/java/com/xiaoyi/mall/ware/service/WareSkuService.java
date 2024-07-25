package com.xiaoyi.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-13 23:29:57
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageInfo queryPage(Map<String, Object> params);
}

