package com.example.demo.model;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class User {
	
	private Long id;
	private String username;
	private String password;
  
	private Set<Role> roles;

	public User() {
	}
	
	public User(Map<String, Object> map) {
		this.username = (String)map.get("username");
		this.password = (String)map.get("password");

		Set<Role> rolesSet = new HashSet<Role>();
		for (HashMap<String, String> role : (List<HashMap<String, String>>)map.get("roles")) {
			rolesSet.add(new Role((String)role.get("name")));
		}
		
		setRoles(rolesSet);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
  
}
