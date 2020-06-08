package com.demo.tenant;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceConfig {

	@Profile("dev")
	@Bean
	@ConfigurationProperties(prefix = "dev.datasource")
	public DataSource dataSourceDev() {
		return DataSourceBuilder.create().build();
	}
	
	@Profile("prod")
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSourceProd() {
		return DataSourceBuilder.create().build();
	}
}
