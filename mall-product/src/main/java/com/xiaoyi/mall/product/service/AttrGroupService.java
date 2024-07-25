package com.xiaoyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageInfo queryPage(Map<String, Object> params);

    PageInfo queryBaseAttrPage(Map<String, Object> params, String categoryId);

    AttrGroupEntity selectById(Long attrGroupId);
}

