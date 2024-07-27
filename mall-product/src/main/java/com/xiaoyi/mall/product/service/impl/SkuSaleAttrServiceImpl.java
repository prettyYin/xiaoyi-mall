package com.xiaoyi.mall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.product.dao.SkuSaleAttrDao;
import com.xiaoyi.mall.product.entity.SkuSaleAttrValueEntity;
import com.xiaoyi.mall.product.service.SkuSaleAttrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SkuSaleAttrServiceImpl extends ServiceImpl<SkuSaleAttrDao, SkuSaleAttrValueEntity> implements SkuSaleAttrService {
}
