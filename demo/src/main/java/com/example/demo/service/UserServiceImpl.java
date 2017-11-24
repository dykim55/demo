package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
  
	@Override  
	public void saveUser(User user, String[] roles) throws Exception {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<Role> rolesSet = new HashSet<Role>();
		for (String role : roles) {
			rolesSet.add(new Role(role));
		}
		user.setRoles(rolesSet);
		
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) throws Exception {
		return userRepository.findByUsername(username);
	}
  
}
