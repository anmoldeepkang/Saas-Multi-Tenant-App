package com.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Tenant;
import com.demo.repository.TenantRepository;

@Transactional
@Repository("TenantRepository")
public class TenantRepositoryImpl implements TenantRepository {

	private EntityManager entityManager;
	
	public TenantRepositoryImpl(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	
	@Override
	public Tenant saveTenant(Tenant tenant) {
		entityManager.persist(tenant);
		return tenant;
	}

	@Override
	public List<Tenant> findAll() {
		//entityManager.find(Tenant.class, );
		return entityManager.createQuery("select t from Tenant t",Tenant.class).getResultList();
	}

	@Override
	public Tenant getTenantById(String id) {
		return entityManager.find(Tenant.class,id);
	}

	@Override
	public Tenant updateTenant(Tenant tenant) {
		return entityManager.merge(tenant);
	}

	@Override
	public void deleteTenant(String username) {
		Tenant tenant=getTenantByUserName(username);
		if(tenant!=null)
		entityManager.remove(tenant);
		
	}

	@Override
	public Tenant getTenantByUserName(String username) {
		javax.persistence.Query query=entityManager.createQuery("from Tenant t where t.username=:username");
		query.setParameter("username", username);
		Tenant t=(Tenant) query.getResultList().get(0);
		return t;
		
	}
	

}
