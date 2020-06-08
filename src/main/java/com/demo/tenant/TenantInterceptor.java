package com.demo.tenant;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.oauth2.CustomUserDetailsService;
import com.demo.oauth2.TokenProvider;
import com.demo.repository.UserRepository;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {
	 
	List<String> publisUrls=Arrays.asList("/auth","/oauth","/error","/user");
	Logger logger = LoggerFactory.getLogger(getClass());
    {
        logger.debug("Creating TenantInterceptor interceptor");
    }
    
	@Autowired
	UserRepository repository;
	
	@Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
	 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    		
    		
    			String jwt = getJwtFromRequest(request);

                if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                    String userId = tokenProvider.getUserIdFromToken(jwt);

                    UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                    TenantContext.setCurrentTenant(userDetails.getUsername());
                    TenantContext.setTenantSchema(userDetails.getUsername());
                    
                }
    		
    		
            
    	
    	return true;
    }
    
  


	@Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        TenantContext.clear();
    }
    
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
