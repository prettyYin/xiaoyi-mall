package com.xiaoyi.mall.product.service.impl;

import com.xiaoyi.mall.product.vo.SpuSaveVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.product.dao.SpuImagesDao;
import com.xiaoyi.mall.product.entity.SpuImagesEntity;
import com.xiaoyi.mall.product.service.SpuImagesService;

@Slf4j
@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageInfo(page);
    }

    @Override
    public void saveSpuImages(Long spuId, SpuSaveVo saveVo) {
        List<SpuImagesEntity> saveSpuImageList = new ArrayList<>();
        saveVo.getImages().forEach(imgUrl -> {
            SpuImagesEntity spuImage = new SpuImagesEntity(spuId, imgUrl);
            saveSpuImageList.add(spuImage);
        });
        boolean isSuccess = saveBatch(saveSpuImageList);
        log.info("保存商品详情图片信息是否成功：{}", isSuccess);
    }

}