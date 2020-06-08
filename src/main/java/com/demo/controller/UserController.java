package com.demo.controller;

import java.util.List;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exceptions.ResourceNotFoundException;
import com.demo.model.User;
import com.demo.oauth2.CurrentUser;
import com.demo.oauth2.UserPrincipal;
import com.demo.repository.UserRepository;

@RestController
public class UserController {

    private UserRepository userRepository;

    private DataSource dataSource;
    

    public UserController(UserRepository repository, DataSource dataSource) {
        this.userRepository = repository;
        this.dataSource = dataSource;
    }

    
    
    @DeleteMapping("deleteUser/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String username){
		if (username != SecurityContextHolder.getContext().getAuthentication().getName()
				|| !SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ADMIN")) {
			return;
		}
    	else {
    		userRepository.deleteUser(username);
    	}
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
    	User user=userRepository.getUserByEmail(userPrincipal.getEmail());
    	if(user==null)
    		throw new ResourceNotFoundException("User", "id", userPrincipal.getId());
		return user;
        
    }

}
