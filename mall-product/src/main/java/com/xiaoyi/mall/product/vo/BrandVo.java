package com.xiaoyi.mall.product.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BrandVo {

    /**
     * "brandId": 0,
     * "brandName": "string",
     */
    private Long brandId;
    private String  brandName;
}
