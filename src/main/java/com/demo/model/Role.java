package com.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="username")
    private String username;

    @Column(name="authorities")
    private String authorities;

    @Column(name="username")
	public String getUsername() {
		return username;
	}

    @Column(name="username")
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="authorities")
	public String getAuthorities() {
		return authorities;
	}

	@Column(name="authorities")
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

   
}
