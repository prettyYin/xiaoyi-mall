package com.xiaoyi.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoyi.mall.common.enums.PurchaseEnum;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.ware.dao.PurchaseDao;
import com.xiaoyi.mall.ware.entity.PurchaseEntity;
import com.xiaoyi.mall.ware.service.PurchaseService;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageInfo(page);
    }

    @Override
    public PageInfo unreceiveList() {
        // 查询状态未被领取的采购单列表
        LambdaQueryWrapper<PurchaseEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(PurchaseEntity::getStatus, PurchaseEnum.RECEIVE.getStatus());
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(null),
                queryWrapper
        );
        return new PageInfo(page);
    }

}