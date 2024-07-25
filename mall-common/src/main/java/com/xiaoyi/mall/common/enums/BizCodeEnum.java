package com.xiaoyi.mall.common.enums;

/***
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为5为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 * 错误码列表：
 *  10: 通用
 *      001：参数格式校验
 *  11: 商品
 *  12: 订单
 *  13: 购物车
 *  14: 物流
 *
 *
 */
public enum BizCodeEnum {
    SQL_SYNTAX_EXCEPTION(500, "SQL语法错误"),
    UNAUTHORIZED_EXCEPTION(401, "未授权"),
    FORBIDDEN_EXCEPTION(403, "禁止访问"),
    NOT_FOUND_EXCEPTION(404, "未找到资源"),
    METHOD_NOT_ALLOWED_EXCEPTION(405, "请求方法不允许"),
    REQUEST_TIMEOUT_EXCEPTION(408, "请求超时"),
    CONFLICT_EXCEPTION(409, "请求冲突"),
    GONE_EXCEPTION(410, "请求资源已过期"),
    UNSUPPORTED_MEDIA_TYPE_EXCEPTION(415, "不支持的媒体类型"),
    TOO_MANY_REQUESTS_EXCEPTION(429, "请求过多"),
    HTTP_SERVER_EXCEPTION(503, "HTTP请求错误"),

    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VAILD_EXCEPTION(10001, "参数格式校验失败");

    private int code;
    private String msg;
    BizCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
