package com.memorynotfound.service;

import com.memorynotfound.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(String id);
}
