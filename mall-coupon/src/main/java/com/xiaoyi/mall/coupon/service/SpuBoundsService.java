package com.xiaoyi.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-13 23:17:43
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageInfo queryPage(Map<String, Object> params);
}

