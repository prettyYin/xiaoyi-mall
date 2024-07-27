package com.xiaoyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.product.entity.SkuInfoEntity;
import com.xiaoyi.mall.product.vo.Skus;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageInfo queryPage(Map<String, Object> params);

    void saveSkuAllInfos(Long spuId, Long branId, Long catalogId, List<Skus> skuVos);

    PageInfo queryPageByCondition(Map<String, Object> params);
}

