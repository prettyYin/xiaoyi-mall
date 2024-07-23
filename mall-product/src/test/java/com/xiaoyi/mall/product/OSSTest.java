package com.xiaoyi.mall.product;


import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
public class OSSTest {

    @Resource
    private OSSClient ossClient;

    @Test
    public void test() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("D:\\MyUserData\\Desktop\\OAuth2.png");
        ossClient.putObject("chandler-mall","OAuth2.png",inputStream);
        ossClient.shutdown();
        System.out.println("上传成功");
    }
}
