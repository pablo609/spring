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
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(String id) {
        return users.stream().filter(user -> user.getUserId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean exists(User user) {
        return users.stream().anyMatch(currentUser -> currentUser.getName().equals(user.getName()));
    }

    @Override
    public void create(User user) {
        user.setUserId(getNextUserId());
        users.add(user);
    }

    @Override
    public void delete(String userId) {
        User user = getById(userId);

        users.remove(user);
    }

    private String getNextUserId() {
        return String.valueOf(usersCounter.incrementAndGet());
    }
}
