package com.demo.tenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContext {
	private static Logger logger = LoggerFactory.getLogger(TenantContext.class.getName());
	private static ThreadLocal<String> currentTenant = new ThreadLocal<>();
    
	public static void setCurrentTenant(String tenant) {
        logger.debug("Setting tenant to " + tenant);
        currentTenant.set(tenant);
    }
	
	private static ThreadLocal<String> tenantSchema = new ThreadLocal<>();

    public static String getTenantSchema() {
        return tenantSchema.get();
    }

    public static void setTenantSchema(String uuid) {
        tenantSchema.set(uuid);
    }
    
	public static String getCurrentTenant() {
        return currentTenant.get();
    }
    
	public static void clear() {
        currentTenant.set(null);
    }
}
