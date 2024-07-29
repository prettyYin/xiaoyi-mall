package com.xiaoyi.mall.ware.service.impl;

import cn.hutool.core.util.StrUtil;
import com.xiaoyi.mall.common.enums.WareConstant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.ware.dao.PurchaseDetailDao;
import com.xiaoyi.mall.ware.entity.PurchaseDetailEntity;
import com.xiaoyi.mall.ware.service.PurchaseDetailService;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        /*
          status: 0,//状态
             wareId: 1,//仓库id
         */

        QueryWrapper<PurchaseDetailEntity> queryWrapper = new QueryWrapper<>();

        String key = (String) params.get("key");
        if(!StrUtil.isEmpty(key)){
            //purchase_id  sku_id
            queryWrapper.and(w-> w.eq("purchase_id",key).or().eq("sku_id",key));
        }

        String status = (String) params.get("status");
        if(!StrUtil.isEmpty(status)){
            //purchase_id  sku_id
            queryWrapper.eq("status",status);
        }

        String wareId = (String) params.get("wareId");
        if(!StrUtil.isEmpty(wareId)){
            //purchase_id  sku_id
            queryWrapper.eq("ware_id",wareId);
        }
        IPage<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params),
                queryWrapper
        );

        return new PageInfo(page);
    }

    @Override
    public void updateReceivingByPurchaseIds(List<Long> purchaseIds) {
        if (purchaseIds.isEmpty()) {
            return;
        }
        List<PurchaseDetailEntity> purchaseDetails = lambdaQuery().in(PurchaseDetailEntity::getPurchaseId, purchaseIds).list();
        purchaseDetails.forEach(purchaseDetail -> purchaseDetail.setStatus(WareConstant.PurchaseDetailEnum.RECEIVING.getStatus()));
        updateBatchById(purchaseDetails);
    }

}