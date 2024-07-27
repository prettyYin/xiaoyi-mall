package com.xiaoyi.mall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xiaoyi.mall.common.utils.R;
import com.xiaoyi.mall.product.feign.CouponFeignService;
import com.xiaoyi.mall.product.service.*;
import com.xiaoyi.mall.product.to.SaveSpuBoundsTo;
import com.xiaoyi.mall.product.vo.Bounds;
import com.xiaoyi.mall.product.vo.Skus;
import com.xiaoyi.mall.product.vo.SpuSaveVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.product.dao.SpuInfoDao;
import com.xiaoyi.mall.product.entity.SpuInfoEntity;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    private final SpuInfoDescService spuInfoDescService;
    private final SpuImagesService spuImagesService;
    private final ProductAttrValueService productAttrValueService;
    private final SkuInfoService skuInfoService;
    private final CouponFeignService couponFeignService;

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageInfo(page);
    }

    /**
     * 保存商品详情数据
     */
    @Transactional
    @Override
    public void saveDetail(SpuSaveVo saveVo) {
        // 1.  保存mall-pms.pms_spu_info信息（spu商品基本信息）
        Long spuId = saveSpuInfo(saveVo);
        if (ObjectUtil.isNull(spuId)) {
            return;
        }
        // 2.  保存mall-pms.pms_spu_info_desc信息（spu商品图片介绍信息）
        spuInfoDescService.saveSpuInfoDesc(spuId, saveVo.getDecript());
        // 3.  保存mall-pms.pms_spu_images信息（spu商品详情图信息）
        spuImagesService.saveSpuImages(spuId, saveVo);
        // 4.  保存mall-pms.pms_product_attr_value信息（spu商品基本属性信息）
        productAttrValueService.saveBaseAttrs(spuId, saveVo);
        // 5.  保存mall-sms.sms_spu_bounds信息（spu商品积分信息）
        Bounds bounds = saveVo.getBounds();
        saveSpuBounds(spuId, bounds);
        // 6.  保存spu对应的sku信息
        List<Skus> skuVos = saveVo.getSkus();
        // 6.1 保存mall-pms.pms_sku_info信息（sku商品基本信息）
        // 6.2 保存mall-pms.pms_sku_images信息（sku商品图片信息）
        // 6.3 保存mall-pms.pms_sku_sale_attr_value信息（sku商品销售属性信息）
        // 6.4 保存mall-sms.sms_sku_full_reduction（sku商品满减信息）
        // 6.5 保存mall-sms.sms_sku_ladder信息（sku商品折扣信息）
        skuInfoService.saveSkuAllInfos(spuId, saveVo.getBrandId(), saveVo.getCatalogId(), skuVos);

    }

    private void saveSpuBounds(Long spuId, Bounds bounds) {
        SaveSpuBoundsTo saveTo = new SaveSpuBoundsTo(spuId, bounds.getGrowBounds(), bounds.getBuyBounds());
        R r = couponFeignService.saveSpuBounds(saveTo);
        log.info("保存spu商品积分信息是否成功：{}", r.getCode() == 0);
    }

    private Long saveSpuInfo(SpuSaveVo saveVo) {
        SpuInfoEntity spuInfo = new SpuInfoEntity();
        BeanUtil.copyProperties(saveVo, spuInfo);
        SpuInfoService proxy = (SpuInfoService) AopContext.currentProxy();
        boolean isSuccess = proxy.save(spuInfo);
        log.info("保存商品详情数据是否成功：{}", isSuccess);
        return spuInfo.getId();
    }

}