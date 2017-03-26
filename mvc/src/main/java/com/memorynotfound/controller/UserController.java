package com.memorynotfound.controller;

import com.memorynotfound.model.User;
import com.memorynotfound.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Getting all users");

        List<User> users = userService.getAll();
        if(users == null || users.size() == 0) {
            log.info("No users were found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        log.info("Getting user with id: " + userId);

        User user = userService.getById(userId);
        if(user == null) {
            log.info("Unable to find user with id: " + userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        log.info("Creating new user: " + user.toString());

        if(userService.exists(user)) {
            log.info("A user with name " + user.getName() + " exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.create(user);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        log.info("Updating user: " + user.toString());
        User currentUser = userService.getById(userId);

        if(currentUser == null) {
            log.info("User with id " + userId + " doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setNickName(user.getNickName());

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        log.info("Deleting user with id " + userId);
        User currentUser = userService.getById(userId);

        if(currentUser == null) {
            log.info("User with id " + userId + " doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(userId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
