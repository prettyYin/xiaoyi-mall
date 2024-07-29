package com.xiaoyi.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoyi.mall.common.enums.WareConstant;
import com.xiaoyi.mall.ware.entity.PurchaseDetailEntity;
import com.xiaoyi.mall.ware.exception.entity.CustomException;
import com.xiaoyi.mall.ware.service.PurchaseDetailService;
import com.xiaoyi.mall.ware.vo.MergeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.ware.dao.PurchaseDao;
import com.xiaoyi.mall.ware.entity.PurchaseEntity;
import com.xiaoyi.mall.ware.service.PurchaseService;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    private final PurchaseDetailService purchaseDetailService;
    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageInfo(page);
    }

    @Override
    public PageInfo queryPageUnreceiveList() {
        // 查询状态未被领取的采购单列表
        LambdaQueryWrapper<PurchaseEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(PurchaseEntity::getStatus, WareConstant.PurchaseEnum.CREATED.getStatus())
                .or()
                .eq(PurchaseEntity::getStatus, WareConstant.PurchaseEnum.ASSIGNED.getStatus());
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(null),
                queryWrapper
        );
        return new PageInfo(page);
    }

    @Transactional
    @Override
    public void mergePurchaseDetail(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        // 没有订单id，创建新订单
        if (purchaseId == null) {
            PurchaseEntity purchase = new PurchaseEntity().setStatus(WareConstant.PurchaseDetailEnum.CREATED.getStatus());
            save(purchase);
            purchaseId = purchase.getId();
        }
        List<Long> ids = mergeVo.getItems();
        // 确认采购单状态是新建状态或者已分配状态才可以进行合并
        List<PurchaseDetailEntity> purchaseDetailList = purchaseDetailService.listByIds(ids);
        List<PurchaseDetailEntity> filters = purchaseDetailList.stream().filter(purchaseDetail -> purchaseDetail.getStatus().equals(WareConstant.PurchaseEnum.CREATED.getStatus()) || purchaseDetail.getStatus().equals(WareConstant.PurchaseEnum.ASSIGNED.getStatus())).collect(Collectors.toList());
        if (filters.size() != purchaseDetailList.size()) {
            log.warn("只允许合并新建/已分配状态");
            throw new CustomException("只允许合并新建/已分配状态");
        }
        // 合并订单并修改为已分配
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetailEntity> purchaseDetails = ids.stream()
                .map(id -> new PurchaseDetailEntity()
                        .setId(id)
                        .setPurchaseId(finalPurchaseId)
                        .setStatus(WareConstant.PurchaseDetailEnum.ASSIGNED.getStatus()))
                .collect(Collectors.toList());
        purchaseDetailService.updateBatchById(purchaseDetails, purchaseDetails.size());
    }

}