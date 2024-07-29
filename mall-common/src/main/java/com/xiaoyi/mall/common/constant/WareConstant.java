package com.xiaoyi.mall.common.constant;

public class WareConstant {

    public enum PurchaseEnum {
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        RECEIVE(2, "已领取"),
        FINISH(3, "已完成"),
        HASERROR(4, "有异常");
        ;

        private final int status;
        private final String statusName;

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

    public enum PurchaseDetailEnum {
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        BUYING(2, "正在采购"),
        FINISH(3, "已完成"),
        HASERROR(4, "采购失败");

        private final int status;
        private final String statusName;

        PurchaseDetailEnum(int status, String statusName) {
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

}
