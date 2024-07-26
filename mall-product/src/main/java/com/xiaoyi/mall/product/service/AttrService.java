package com.xiaoyi.mall.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.product.entity.AttrEntity;
import com.xiaoyi.mall.product.vo.AttrGroupRelationVo;
import com.xiaoyi.mall.product.vo.AttrRespVo;
import com.xiaoyi.mall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 */
public interface AttrService extends IService<AttrEntity> {

    PageInfo queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageInfo queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);

    PageInfo getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

}

