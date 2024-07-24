package com.xiaoyi.mall.thirdparty.config;

import io.minio.MinioClient;
import lombok.SneakyThrows;
// 修改为自己项目的类路径
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minio配置类
 *
 * @author xiaoyi
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
@ConditionalOnProperty(value = "oss.name", havingValue = "minio")
public class MinioConfiguration {
 
    private final MinioProperties ossProperties;

    public MinioConfiguration(MinioProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Bean
    @SneakyThrows
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(ossProperties.getEndpoint())
                .credentials(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                .build();
    }
 
}