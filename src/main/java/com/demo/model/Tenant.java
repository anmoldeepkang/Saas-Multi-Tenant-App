package com.demo.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="tenants")
public class Tenant  {

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String tenant_id;
    
	
    @Column(name = "tenant_name")
    private String tenantName;

    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "username")
    public String getUsername() {
		return username;
	}

	@Column(name = "password")
    private String password;
	
    @Column(name = "username")
	public void setUsername(String username) {
		this.username = username;
	}

    @Column(name = "password")
	public String getPassword() {
		return password;
	}

    @Column(name = "password")
	public void setPassword(String password) {
		this.password = password;
	}


    public String getTenantId() {
        return tenant_id;
    }

    public void setTenantId(String tenantId) {
        this.tenant_id = tenantId;
    }
    
    

    @Column(name = "tenant_name")
    public String getTenantName() {
        return tenantName;
    }

    @Column(name = "tenant_name")
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
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
