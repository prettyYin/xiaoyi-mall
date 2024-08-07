package com.xiaoyi.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xiaoyi.mall.common.valid.UpdateGroup;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 商品三级分类
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
@Data
@Accessors(chain = true)
@TableName("pms_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@NotNull(groups = UpdateGroup.class)
	@TableId
	private Long catId;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 父分类id
	 */
	private Long parentCid;
	/**
	 * 层级
	 */
	private Integer catLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	private Integer showStatus;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 图标地址
	 */
	private String icon;
	/**
	 * 计量单位
	 */
	private String productUnit;
	/**
	 * 商品数量
	 */
	private Integer productCount;

	/**
	 * 子菜单
	 */
	@TableField(exist = false)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<CategoryEntity> children;
}
