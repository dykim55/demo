package com.example.demo.user;

import java.util.List;

import com.example.demo.model.User;

public interface UserMapper {
     public User readUser(String username);
     public List<String> readAuthority(String username);
}
