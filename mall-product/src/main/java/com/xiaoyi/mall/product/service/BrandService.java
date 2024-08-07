package com.xiaoyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
public interface BrandService extends IService<BrandEntity> {

    PageInfo queryPage(Map<String, Object> params);

    /**
     * 级联修改
     * @param brand 品牌对象
     */
    void updateCascade(BrandEntity brand);
}

