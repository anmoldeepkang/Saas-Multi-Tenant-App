package com.demo.controller;

import java.net.URI;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.exceptions.BadRequestException;
import com.demo.model.User;
import com.demo.oauth2.AuthProvider;
import com.demo.oauth2.LoginRequest;
import com.demo.oauth2.SignUpRequest;
import com.demo.oauth2.TokenProvider;
import com.demo.repository.UserRepository;
import com.demo.response.ApiResponse;
import com.demo.response.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;
    
    @Autowired
    DataSource dataSource;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
       
		 
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		/*
		 * if(userRepository.getUserByUserName(signUpRequest.getEmail())) { throw new
		 * BadRequestException("Email address already in use."); }
		 */

        // Creating user's account
        User user = new User();
        user.setUsername(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.saveUser(user);
        Flyway flyway = new Flyway();
	    flyway.setLocations("db/migration/tenants");
	    flyway.setDataSource(dataSource);
	    flyway.setSchemas(user.getEmail());
	    flyway.migrate();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getTenantId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}
