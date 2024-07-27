package com.xiaoyi.mall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity {

    @TableId(type = IdType.INPUT)
    private Long spuId;
    private String decript;
}
