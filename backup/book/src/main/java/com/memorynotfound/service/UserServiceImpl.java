package com.memorynotfound.service;

import com.memorynotfound.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {
    private static final AtomicInteger usersCounter = new AtomicInteger();
    List<User> users = new ArrayList<>(
            Arrays.asList(
                    new User(getNextUserId(), "User 1", "Nick 1"),
                    new User(getNextUserId(), "User 2", "Nick 2")));

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    private String getNextUserId() {
        return String.valueOf(usersCounter.incrementAndGet());
    }
}
