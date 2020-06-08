package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.demo.oauth2.AppProperties;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(AppProperties.class)
public class SpringBootWebApplication extends SpringBootServletInitializer  {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder 
  application) {
      return application.sources(SpringBootWebApplication.class);
     }
}
