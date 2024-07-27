package com.xiaoyi.mall.coupon.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaveSkuFullReductionTo {

    private Long skuId;

    private BigDecimal fullPrice;

    private BigDecimal reducePrice;

    private Integer addOther;

    public SaveSkuFullReductionTo(Long skuId, BigDecimal fullPrice, BigDecimal reducePrice) {
        this.skuId = skuId;
        this.fullPrice = fullPrice;
        this.reducePrice = reducePrice;
    }
}