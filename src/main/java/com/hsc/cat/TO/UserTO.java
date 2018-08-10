package com.hsc.cat.TO;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserTO implements UserDetails {

	private String username;

	private String password;

	private String role;
	
	Set<GrantedAuthority> authorities;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Set<GrantedAuthority> getAuthorities() {
		return  authorities;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	

	public UserTO() {
		
		
	}

	public UserTO(String username, String password, String role, Set<GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.authorities = authorities;
	}
	
	
	
}
