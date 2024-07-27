package com.xiaoyi.mall.product.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaveSkuLadderTo {

    private Long skuId;

    private Integer fullCount;

    private BigDecimal discount;

    private BigDecimal price;

    private Integer addOther;

    public SaveSkuLadderTo(Long skuId, Integer fullCount, BigDecimal discount, BigDecimal price) {
        this.skuId = skuId;
        this.fullCount = fullCount;
        this.discount = discount;
        this.price = price;
    }
}
