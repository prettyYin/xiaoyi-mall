package com.xiaoyi.mall.thirdparty.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.xiaoyi.mall.common.utils.R;
import com.xiaoyi.mall.thirdparty.config.AliyunProperties;
import com.xiaoyi.mall.thirdparty.config.MinioProperties;
import io.minio.MinioClient;
import io.minio.PostPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping("/thirdparty/oss")
@RestController
@Slf4j
@RequiredArgsConstructor
public class OSSController {

    private final MinioProperties minioProperties;
    private final AliyunProperties aliyunProperties;
    private final MinioClient minioClient;
    @Resource
    private OSSClient ossClient;

    /**
     * 阿里云OSS上传接口
     * @return 签名信息对象
     */
    @RequestMapping("/policy")
    public R policy() {
        //https://gulimall-hello.oss-cn-beijing.aliyuncs.com/hahaha.jpg
        String host = "https://" + aliyunProperties.getBucketName() + "." + aliyunProperties.getEndpoint(); // host的格式为 bucketname.endpoint
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//        String callbackUrl = "http://88.88.88.88:8888";


        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, aliyunProperties.getDir());

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<>();
            respMap.put("accessid", aliyunProperties.getAccessKey());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", aliyunProperties.getDir());
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));
        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        }

        return R.ok().put("data",respMap);
    }

    /**
     * MinIO上传接口，但前端未做适配
     * @return 签名信息对象
     */
//    @GetMapping("/policy")
    public R uploadFile() {
        try {
            PostPolicy postPolicy = new PostPolicy(minioProperties.getBucketName(), ZonedDateTime.now().plusMinutes(10));
            postPolicy.addStartsWithCondition("key", minioProperties.getDir());
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
