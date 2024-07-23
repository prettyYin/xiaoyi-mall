package com.xiaoyi.mall.product.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

//@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@SneakyThrows
	@Override
	protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		//创建消息转换器对象
		MappingJackson2HttpMessageConverter messageConverter=new MappingJackson2HttpMessageConverter();
		//设置对象转换器，底层使用Jackson将Java对象转为json
		messageConverter.setObjectMapper(new JacksonObjectMapper());
		//将上面的消息转换器对象追加到mvc框架的转换器集合中
		converters.add(0,messageConverter);
	}
}
