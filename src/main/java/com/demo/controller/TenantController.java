package com.demo.controller;

import java.util.List;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
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

import com.demo.model.Tenant;
import com.demo.repository.TenantRepository;

@RestController
public class TenantController {

    private TenantRepository repository;

    private DataSource dataSource;
    

    public TenantController(TenantRepository repository, DataSource dataSource) {
        this.repository = repository;
        this.dataSource = dataSource;
    }

    @PostMapping(value="/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Tenant createTenant(@RequestBody Tenant tenant){
        repository.saveTenant(tenant);
        Flyway flyway = new Flyway();
        flyway.setLocations("db/migration/tenants");
        flyway.setDataSource(dataSource);
        flyway.setSchemas(tenant.getTenantId());
        flyway.migrate();
        return tenant;
    }
    
    
    
    @DeleteMapping("deleteUser/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTenant(@PathVariable String username){
		if (username != SecurityContextHolder.getContext().getAuthentication().getName()
				|| !SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ADMIN")) {
			return;
		}
    	else {
    		repository.deleteTenant(username);
    	}
    }

    @GetMapping("/users")
    public List<Tenant> getTenants(){
        return repository.findAll();
    }

}
