package com.demo.repository;


import java.util.List;

import com.demo.model.Tenant;

public interface TenantRepository {

	Tenant saveTenant(Tenant tenant);

	List<Tenant> findAll();
	
	Tenant getTenantById(String id);
	
	Tenant updateTenant(Tenant tenant);
	
	void deleteTenant(String uuid);
	
	Tenant getTenantByUserName(String username);
	
	
	
}
