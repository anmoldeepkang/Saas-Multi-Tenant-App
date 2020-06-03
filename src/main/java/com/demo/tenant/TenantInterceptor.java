package com.demo.tenant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.repository.TenantRepository;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {
	 
	Logger logger = LoggerFactory.getLogger(getClass());
    {
        logger.debug("Creating TenantInterceptor interceptor");
    }
    
	@Autowired
	TenantRepository repository;
	 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	String tenantUuid = request.getHeader("tenant-uuid");
    	String tenantSchema=null;
    	if(tenantUuid!=null) {
    		if(repository.getTenantById(tenantUuid)==null)
    			return false;
    	}
        
        logger.debug("Set TenantContext: {}",tenantSchema);
        TenantContext.setTenantSchema(tenantSchema);
        return true;
    }
    
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        TenantContext.clear();
    }
}
