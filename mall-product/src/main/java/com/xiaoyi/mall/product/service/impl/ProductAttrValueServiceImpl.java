package com.xiaoyi.mall.product.service.impl;

import com.xiaoyi.mall.product.vo.BaseAttrs;
import com.xiaoyi.mall.product.vo.SpuSaveVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.product.dao.ProductAttrValueDao;
import com.xiaoyi.mall.product.entity.ProductAttrValueEntity;
import com.xiaoyi.mall.product.service.ProductAttrValueService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );

        return new PageInfo(page);
    }

    @Override
    public void saveProductAttr(List<ProductAttrValueEntity> collect) {
        this.saveBatch(collect);
    }

    @Override
    public List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId) {
        List<ProductAttrValueEntity> entities = this.baseMapper.selectList(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id", spuId));
        return entities;
    }

    @Transactional
    @Override
    public void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities) {
        //1、删除这个spuId之前对应的所有属性
        this.baseMapper.delete(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id",spuId));

        List<ProductAttrValueEntity> collect = entities.stream().peek(item -> item.setSpuId(spuId)).collect(Collectors.toList());
        this.saveBatch(collect);
    }

    @Override
    public void saveBaseAttrs(Long spuId, SpuSaveVo saveVo) {
        List<BaseAttrs> baseAttrs = saveVo.getBaseAttrs();
        List<ProductAttrValueEntity> saveProductAttrs = new ArrayList<>();
        baseAttrs.forEach(attr -> {
            ProductAttrValueEntity productAttrValue = new ProductAttrValueEntity()
                    .setSpuId(spuId)
                    .setAttrId(attr.getAttrId())
                    .setAttrValue(attr.getAttrValues())
                    .setQuickShow(attr.getShowDesc());
            saveProductAttrs.add(productAttrValue);
        });
        boolean isSuccess = saveBatch(saveProductAttrs);
        log.info("保存商品基本属性信息是否成功：{}", isSuccess);
    }

}