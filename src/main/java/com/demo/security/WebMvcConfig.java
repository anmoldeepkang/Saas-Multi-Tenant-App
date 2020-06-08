package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.tenant.TenantInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  TenantInterceptor tenantInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(tenantInterceptor);
  }

  private final long MAX_AGE_SECS = 3600;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
      .allowedOrigins("*")
      .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
      .allowedHeaders("*")
      .allowCredentials(true)
      .maxAge(MAX_AGE_SECS);
  }
}
