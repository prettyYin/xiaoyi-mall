package com.xiaoyi.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * 秒杀活动
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-13 23:17:43
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageInfo queryPage(Map<String, Object> params);
}

