package org.domainname.config;



import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.domainname.config.SecurityConfig;

import org.springframework.web.multipart.support.MultipartFilter;
import javax.servlet.ServletContext;

public class AptMgrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class ,PersistenceJPAConfig.class };
	}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {MultipartUploadConfig.class, SecurityConfig.class};
	}
	

	
}
