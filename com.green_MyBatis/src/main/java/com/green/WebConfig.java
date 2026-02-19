package com.green;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
   
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registy) {
		registy.addResourceHandler("/img/**")
		       .addResourceLocations("file:///C:/Spring_Boot/com.green_MyBatis/frontend/public/img/");
	}
}
