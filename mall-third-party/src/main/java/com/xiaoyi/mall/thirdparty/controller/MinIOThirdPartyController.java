package com.xiaoyi.mall.thirdparty.controller;

import com.xiaoyi.mall.common.utils.R;
import com.xiaoyi.mall.thirdparty.config.MinioProperties;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PostPolicy;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Map;

@RequestMapping("/thirdparty/oss")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MinIOThirdPartyController {

    private final MinioProperties ossProperties;
    private final MinioClient minioClient;
    private final String objectNamePrefix = "uploads/";

    @GetMapping("/policy")
    public R uploadFile() {
        try {
            PostPolicy postPolicy = new PostPolicy(ossProperties.getBucketName(), ZonedDateTime.now().plusMinutes(10));
            postPolicy.addStartsWithCondition("key", objectNamePrefix);
            Map<String, String> result = minioClient.getPresignedPostFormData(postPolicy);
            for (Map.Entry<String, String> entry : result.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
            return R.ok().put("data", result);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return null;
    }
}
