package com.xiaoyi.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.xiaoyi.mall.common.valid.AddGroup;
import com.xiaoyi.mall.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 品牌分类关联
 * 
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
@Data
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 品牌id
	 */
	@NotNull(groups = {AddGroup.class},message = "品牌id不能为空")
	private Long brandId;
	/**
	 * 分类id
	 */
	@NotNull(groups = {AddGroup.class},message = "分类id不能为空")
	private Long catelogId;
	/**
	 * 
	 */
	private String brandName;
	/**
	 * 
	 */
	private String catelogName;

}
