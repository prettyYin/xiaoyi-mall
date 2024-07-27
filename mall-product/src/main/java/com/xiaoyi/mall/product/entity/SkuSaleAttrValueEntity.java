package com.xiaoyi.mall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@TableName("pms_sku_sale_attr_value")
public class SkuSaleAttrValueEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long skuId;

    private Long attrId;

    private String attrName;

    private String attrValue;

    private Integer attrSort;
}
