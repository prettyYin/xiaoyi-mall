package com.xiaoyi.mall.product.exception.entity;

import lombok.Data;

/**
 * 自定义异常类
 */
@Data
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private Object date;

    public CustomException(Integer code,String message){
        this.code    = code;
        this.message = message;
    }

    public CustomException(Integer code,String message,Object date){
        this.code    = code;
        this.message = message;
        this.date    = date;
    }
}

