package com.xiaoyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.product.entity.SpuInfoEntity;
import com.xiaoyi.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-11 22:30:29
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageInfo queryPage(Map<String, Object> params);

    void saveDetail(SpuSaveVo saveVo);

    PageInfo queryPageByCondition(Map<String, Object> params);
}

