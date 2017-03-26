package com.memorynotfound.service;

import com.memorynotfound.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(String id);
    boolean exists(User user);
    void create(User user);
    void delete(String userId);
}
