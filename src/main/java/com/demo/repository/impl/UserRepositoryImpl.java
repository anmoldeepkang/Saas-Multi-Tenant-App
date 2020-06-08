package com.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.User;
import com.demo.repository.UserRepository;

@Transactional(readOnly = false, rollbackFor = {Exception.class})
@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext(unitName="entityManagerFactorySecondary")
	private EntityManager entityManager;
	
//	public UserRepositoryImpl(EntityManager entityManager) {
//		this.entityManager=entityManager;
//	}
	
	@Override
	public User saveUser(User tenant) {
		
		entityManager.persist(tenant);
		return tenant;
	}

	@Override
	public List<User> findAll() {
		//entityManager.find(Tenant.class, );
		
		return entityManager.createQuery("select t from User t",User.class).getResultList();
	}

	@Override
	public User getUserById(String id) {
		return entityManager.find(User.class,id);
	}

	@Override
	public User updateTenant(User tenant) {
		return entityManager.merge(tenant);
	}

	@Override
	public void deleteUser(String email) {
		User tenant=getUserByEmail(email);
		if(tenant!=null)
		entityManager.remove(tenant);
		
	}

	@Override
	public User getUserByEmail(String email) {
		javax.persistence.Query query=entityManager.createQuery("select u from User u where u.email=:email");
		query.setParameter("email", email);
		List<User> users= query.getResultList();
		if(users.isEmpty())
			return null;
		return users.get(0);
		
	}
	

}
