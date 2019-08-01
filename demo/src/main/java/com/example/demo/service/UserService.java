package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
	public void saveUser(User user, String[] roles) throws Exception;
	public User findByUsername(String username) throws Exception;
}
