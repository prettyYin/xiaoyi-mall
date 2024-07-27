package com.xiaoyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.product.entity.SpuImagesEntity;
import com.xiaoyi.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu图片
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageInfo queryPage(Map<String, Object> params);

    void saveSpuImages(Long spuId, SpuSaveVo saveVo);
}

