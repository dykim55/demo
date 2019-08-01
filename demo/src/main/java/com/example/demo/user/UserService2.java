package com.example.demo.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService2 extends UserDetailsService {
    Collection<GrantedAuthority> getAuthorities(String username);
}
