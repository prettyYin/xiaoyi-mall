package com.xiaoyi.mall.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.xiaoyi.mall.common.utils.R;
import com.xiaoyi.mall.product.entity.SkuImagesEntity;
import com.xiaoyi.mall.product.entity.SkuSaleAttrValueEntity;
import com.xiaoyi.mall.product.feign.CouponFeignService;
import com.xiaoyi.mall.product.service.SkuImagesService;
import com.xiaoyi.mall.product.service.SkuSaleAttrService;
import com.xiaoyi.mall.product.to.SaveSkuFullReductionTo;
import com.xiaoyi.mall.product.to.SaveSkuLadderTo;
import com.xiaoyi.mall.product.vo.Images;
import com.xiaoyi.mall.product.vo.Skus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.product.dao.SkuInfoDao;
import com.xiaoyi.mall.product.entity.SkuInfoEntity;
import com.xiaoyi.mall.product.service.SkuInfoService;

import static com.xiaoyi.mall.product.constanst.ProductConstants.DEFAULT_IMG;
import static com.xiaoyi.mall.product.constanst.ProductConstants.ZERO_SALE_COUNT;

@RequiredArgsConstructor
@Slf4j
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    private final SkuImagesService skuImagesService;
    private final SkuSaleAttrService skuSaleAttrService;
    private final CouponFeignService couponFeignService;

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageInfo(page);
    }

    @Override
    public void saveSkuAllInfos(Long spuId, Long branId, Long catalogId, List<Skus> skuVos) {
        skuVos.forEach(skuVo -> {
            // 6.1 保存mall-pms.pms_sku_info信息（sku商品基本信息）
            Long skuId = saveSkuInfo(spuId, branId, catalogId, skuVo);

            // 6.2 保存mall-pms.pms_sku_images信息（sku商品图片信息）
            saveSkuImages(skuVo, skuId);

            // 6.3 保存mall-pms.pms_sku_sale_attr_value信息（sku商品销售属性信息）
            saveSkuSaleAttrValues(skuVo, skuId);

            // 6.4 保存mall-sms.sms_sku_full_reduction（sku商品满减信息）
            saveSkuFullReducation(skuVo, skuId);

            // 6.5 保存mall-sms.sms_sku_ladder信息（sku商品折扣信息）
            saveSkuLadder(skuVo, skuId);
        });
    }

    @Override
    public PageInfo queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();
        /**
         * key:
         * catelogId: 0
         * brandId: 0
         * min: 0
         * max: 0
         */
        String key = (String) params.get("key");
        if(!StrUtil.isEmpty(key)){
            queryWrapper.and((wrapper)->{
                wrapper.eq("sku_id",key).or().like("sku_name",key);
            });
        }

        String catelogId = (String) params.get("catelogId");
        if(!StrUtil.isEmpty(catelogId)&&!"0".equalsIgnoreCase(catelogId)){

            queryWrapper.eq("catalog_id",catelogId);
        }

        String brandId = (String) params.get("brandId");
        if(!StrUtil.isEmpty(brandId)&&!"0".equalsIgnoreCase(catelogId)){
            queryWrapper.eq("brand_id",brandId);
        }

        String min = (String) params.get("min");
        if(!StrUtil.isEmpty(min)){
            queryWrapper.ge("price",min);
        }

        String max = (String) params.get("max");

        if(!StrUtil.isEmpty(max)  ){
            try{
                BigDecimal bigDecimal = new BigDecimal(max);

                if(bigDecimal.compareTo(new BigDecimal("0")) > 0){
                    queryWrapper.le("price",max);
                }
            }catch (Exception e){

            }

        }


        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageInfo(page);
    }

    private void saveSkuLadder(Skus skuVo, Long skuId) {
        SaveSkuLadderTo saveTo = new SaveSkuLadderTo(skuId, skuVo.getFullCount(), skuVo.getDiscount(), skuVo.getPrice());
        R r = couponFeignService.saveSkuLadder(saveTo);
        log.info("保存sku商品折扣信息是否成功：{}", r.getCode() == 0);
    }

    private void saveSkuFullReducation(Skus skuVo, Long skuId) {
        SaveSkuFullReductionTo saveTo = new SaveSkuFullReductionTo(skuId, skuVo.getFullPrice(), skuVo.getReducePrice());
        R r = couponFeignService.saveSkuFullReduction(saveTo);
        log.info("保存sku商品满减信息是否成功：{}", r.getCode() == 0);
    }

    private void saveSkuSaleAttrValues(Skus skuVo, Long skuId) {
        skuVo.getAttr().forEach(attr -> {
            SkuSaleAttrValueEntity skuSaleAttr = new SkuSaleAttrValueEntity()
                    .setSkuId(skuId)
                    .setAttrId(attr.getAttrId())
                    .setAttrName(attr.getAttrName())
                    .setAttrValue(attr.getAttrValue());
            boolean isSuccess = skuSaleAttrService.save(skuSaleAttr);
            log.info("保存sku商品销售属性信息是否成功：{}", isSuccess);
        });
    }

    private Long saveSkuInfo(Long spuId, Long branId, Long catalogId, Skus skuVo) {
        Images defaultImage =
                skuVo.getImages().stream().filter(img -> img.getDefaultImg() == DEFAULT_IMG).findFirst().orElse(new Images());
        SkuInfoEntity skuInfo = new SkuInfoEntity()
                .setSpuId(spuId)
                .setBrandId(branId)
                .setCatalogId(catalogId)
                .setSkuName(skuVo.getSkuName())
                .setPrice(skuVo.getPrice())
                .setSkuTitle(skuVo.getSkuTitle())
                .setSkuSubtitle(skuVo.getSkuSubtitle())
                .setSkuDefaultImg(defaultImage.getImgUrl())
                .setSaleCount(ZERO_SALE_COUNT);
        boolean isSuccess = save(skuInfo);
        log.info("保存sku商品基本属性信息是否成功：{}", isSuccess);

        return skuInfo.getSkuId();
    }

    private void saveSkuImages(Skus skuVo, Long skuId) {
        skuVo.getImages().forEach(image -> {
            SkuImagesEntity skuImagesEntity = new SkuImagesEntity()
                    .setSkuId(skuId)
                    .setImgUrl(image.getImgUrl())
                    .setDefaultImg(image.getDefaultImg());
            boolean isImageSuccess = skuImagesService.save(skuImagesEntity);
            log.info("保存sku商品图片信息是否成功：{}", isImageSuccess);
        });
    }

}