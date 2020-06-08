package com.demo.repository;


import java.util.List;

import com.demo.model.User;

public interface UserRepository {

	User saveUser(User tenant);

	List<User> findAll();
	
	User getUserById(String id);
	
	User updateTenant(User tenant);
	
	void deleteUser(String uuid);
	
	User getUserByEmail(String username);
	
	
	
}
