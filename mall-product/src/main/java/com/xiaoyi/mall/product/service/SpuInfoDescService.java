package com.xiaoyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.product.entity.SpuInfoDescEntity;

import java.util.List;

public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {
    void saveSpuInfoDesc(Long spuId, List<String> decript);
}
