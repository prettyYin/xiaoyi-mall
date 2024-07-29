package com.xiaoyi.mall.common.enums;

public enum PurchaseEnum {

    CREATED(0, "新建"),
    ASSIGNED(1, "已分配"),
    RECEIVE(2, "已领取"),
    FINISH(3, "已完成"),
    HASERROR(4, "有异常");

    private int status;
    private String statusName;

    PurchaseEnum(int status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }
}
