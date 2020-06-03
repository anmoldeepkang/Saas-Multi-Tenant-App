package com.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	  @Autowired
	  private DataSource dataSource;

	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  
			
			  auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(dataSource)
			  .usersByUsernameQuery("select `username`, `password`,1" +
			  " from `tenants` where `username`=?")
			  .authoritiesByUsernameQuery("select `username`, `authorities` " +
			  "from `roles` where `username`=?");
			 
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {

		  http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,"/signup").permitAll()
		  .antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
          .anyRequest().authenticated()
          .and()
          .httpBasic().and().formLogin().and().logout();
	  }
	  
	  @Bean
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	      // ALTHOUGH THIS SEEMS LIKE USELESS CODE,
	      // IT'S REQUIRED TO PREVENT SPRING BOOT AUTO-CONFIGURATION
	      return super.authenticationManagerBean();
	  }
}
