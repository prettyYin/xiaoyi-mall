package com.xiaoyi.mall.ware.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;
import com.xiaoyi.mall.ware.dao.WareInfoDao;
import com.xiaoyi.mall.ware.entity.WareInfoEntity;
import com.xiaoyi.mall.ware.service.WareInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<WareInfoEntity> wareInfoEntityQueryWrapper = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");
        if(!StrUtil.isEmpty(key)){
            wareInfoEntityQueryWrapper.eq(WareInfoEntity::getId, key)
                    .or()
                    .like(WareInfoEntity::getName, key)
                    .or()
                    .like(WareInfoEntity::getAddress, key)
                    .or()
                    .like(WareInfoEntity::getAreacode, key);
        }
        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                wareInfoEntityQueryWrapper
        );

        return new PageInfo(page);
    }

}