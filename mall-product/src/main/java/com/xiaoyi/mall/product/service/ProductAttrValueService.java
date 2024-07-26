package com.xiaoyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageInfo queryPage(Map<String, Object> params);

    void saveProductAttr(List<ProductAttrValueEntity> collect);

    List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId);

    void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities);
}

