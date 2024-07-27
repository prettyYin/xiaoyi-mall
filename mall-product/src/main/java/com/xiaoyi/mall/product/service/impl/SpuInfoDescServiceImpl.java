package com.xiaoyi.mall.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.product.dao.SpuInfoDescDao;
import com.xiaoyi.mall.product.entity.SpuInfoDescEntity;
import com.xiaoyi.mall.product.service.SpuInfoDescService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescDao, SpuInfoDescEntity> implements SpuInfoDescService{

    @Override
    public void saveSpuInfoDesc(Long spuId, List<String> decript) {
        SpuInfoDescEntity spuInfoDesc = new SpuInfoDescEntity(spuId, StrUtil.join(",", decript));
        boolean isSuccess = save(spuInfoDesc);
        log.info("保存商品详情介绍信息是否成功：{}", isSuccess);
    }
}
