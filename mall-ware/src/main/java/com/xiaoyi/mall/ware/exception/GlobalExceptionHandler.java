package com.xiaoyi.mall.ware.exception;

import com.xiaoyi.mall.common.enums.BizCodeEnum;
import com.xiaoyi.mall.common.utils.R;
import com.xiaoyi.mall.ware.exception.entity.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * sql异常
     * @param e 异常信息
     * @return com.xiaoyi.mall.common.utils.R
     **/
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public R sqlSyntaxErrorException(SQLSyntaxErrorException e) {
        log.error("sql异常：{}", e.getMessage());
        return R.error(BizCodeEnum.SQL_SYNTAX_EXCEPTION);
    }
    /**
     * 自定义异常
     * @param e 异常信息
     * @return com.xiaoyi.mall.common.utils.R
     **/
    @ExceptionHandler(CustomException.class)
    public R customException(CustomException e) {
        log.error("自定义异常：{}", e.getMessage());
        return R.error(e.getCode(), e.getMessage());
    }

    /**
     * HttpServerErrorException
     * @param e 异常信息
     * @return com.xiaoyi.mall.common.utils.R
     **/
    @ExceptionHandler(HttpServerErrorException.class)
    public R httpServerErrorException(HttpServerErrorException e) {
        log.error("HttpServerErrorException：{}", e.getMessage());
        return R.error(BizCodeEnum.HTTP_SERVER_EXCEPTION.getCode(),e.getMessage());
    }
    /**
     * 参数校验异常
     * @param e 异常信息
     * @return com.xiaoyi.mall.common.utils.R
     **/
    @ExceptionHandler(BindException.class)
    public R bindException(BindException e) {
        log.error("参数校验异常：{}", e.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        e.getFieldErrors().forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return R.error(BizCodeEnum.VAILD_EXCEPTION, errorMap);
    }

    /**
     * 所有异常拦截
     * @param e 异常信息
     * @return com.xiaoyi.mall.common.utils.R
     **/
    @ExceptionHandler(Exception.class)
    public R bindException(Exception e) {
        log.error("未知异常：{}", e.getMessage());
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION);
    }
}
