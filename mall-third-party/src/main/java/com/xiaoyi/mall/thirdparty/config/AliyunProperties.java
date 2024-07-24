package com.xiaoyi.mall.thirdparty.config;

import com.xiaoyi.mall.thirdparty.enums.OSSConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Configuration
@ConfigurationProperties(prefix = OSSConstant.ALIYUN)
public class AliyunProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String dir;

    public AliyunProperties() {
        this.dir = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // 用户上传文件时指定的前缀。
    }
}
