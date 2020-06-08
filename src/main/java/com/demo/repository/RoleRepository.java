package com.demo.repository;

import java.util.List;

import com.demo.model.Role;
import com.demo.model.User;

public interface RoleRepository {
	
	Role saveRole(Role role);

	List<Role> findRolesForUser(String userName);

}
