package com.demo.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.exceptions.ResourceNotFoundException;
import com.demo.model.User;
import com.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if(user==null)
        	throw new UsernameNotFoundException("User not found with email : " + email);

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userRepository.getUserById(id);
        return UserPrincipal.create(user);
    }
}
