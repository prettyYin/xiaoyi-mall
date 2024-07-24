package com.xiaoyi.mall.thirdparty.config;

import com.xiaoyi.mall.thirdparty.enums.OSSConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
 
/**
 * Minio参数配置类
 *
 * @author xiaoyi
 */
@Data
@Configuration
@ConfigurationProperties(prefix = OSSConstant.MINIO)
public class MinioProperties {
 
	/**
	 * 对象存储名称
	 */
	private String name;
 
	/**
	 * 对象存储服务的URL
	 */
	private String endpoint;
 
	/**
	 * Access key 账户ID
	 */
	private String accessKey;
 
	/**
	 * Secret key 密码
	 */
	private String secretKey;
 
	/**
	 * 默认的存储桶名称
	 */
	private String bucketName;
 
	/**
	 * 可上传的文件后缀名
	 */
	private List<String> fileExt;

	/**
	 * 文件路径
	 */
	private String dir;

	public MinioProperties() {
		this.dir = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // 用户上传文件时指定的前缀。
	}
 
}