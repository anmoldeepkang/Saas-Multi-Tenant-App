package com.demo.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import com.demo.oauth2.AuthProvider;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="DEFAULT_SCHEMA.users")
public class User  {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
	
    @Column(name = "username")
    private String userName;

    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "email")
    public String getEmail() {
		return email;
	}

	@Column(name = "password")
    private String password;
	
    @Column(name = "email")
	public void setEmail(String email) {
		this.email = email;
	}

    @Column(name = "password")
	public String getPassword() {
		return password;
	}

    @Column(name = "password")
	public void setPassword(String password) {
		this.password = password;
	}
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private AuthProvider provider;
    
    @Column(name = "provider")
    public AuthProvider getProvider() {
		return provider;
	}
    @Column(name = "provider")
	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}
   
    @Column(name = "id")
	public String getTenantId() {
        return id;
    }

    @Column(name = "id")
    public void setTenantId(String tenantId) {
        this.id = tenantId;
    }
    
    

    @Column(name = "username")
    public String getUsername() {
        return userName;
    }

    @Column(name = "username")
    public void setUsername(String username) {
        this.userName = username;
    }
    
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Column(name = "created_at")
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Column(name = "updated_at")
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
