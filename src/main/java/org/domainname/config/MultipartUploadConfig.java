package org.domainname.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MultipartUploadConfig {
	
	@Bean(name = "filterMultipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver=new CommonsMultipartResolver();
		//resolver.setMaxUploadSize(8192);
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}
}
