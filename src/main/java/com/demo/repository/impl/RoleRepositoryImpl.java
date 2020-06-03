package com.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.demo.model.Role;
import com.demo.repository.RoleRepository;

public class RoleRepositoryImpl implements RoleRepository {

	private EntityManager entityManager;
	
	public RoleRepositoryImpl(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	
	
	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findRolesForUser(String userName) {
		javax.persistence.Query query=entityManager.createQuery("from roles r where r.username=:username");
		query.setParameter("username", userName);
		List<Role> roles= query.getResultList();
		return roles;
	}

}
