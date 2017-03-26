package com.memorynotfound.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memorynotfound.model.User;
import com.memorynotfound.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {
    private MockMvc mvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    private List<User> getUsersTestData() {
        return new ArrayList<>(
                Arrays.asList(
                        new User("1", "User 1", "Nick 1"),
                        new User("2", "User 2", "Nick 2"),
                        new User("3", "User 3", "Nick 3"),
                        new User("4", "User 4", "Nick 4"),
                        new User("5", "User 5", "Nick 5")));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = getUsersTestData();
        when(userService.getAll()).thenReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.length()", equalTo(5)))
                .andExpect(jsonPath("$[0].name", equalTo(users.get(0).getName())));
    }

    @Test
    public void testGetAllUsersWhenEmptyList() throws Exception {
        List<User> users = getUsersTestData();
        when(userService.getAll()).thenReturn(null);

        mvc.perform(get("/users"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetUserById() throws Exception {
        List<User> users = getUsersTestData();
        int userNumber = users.size() - 1;
        when(userService.getById(users.get(userNumber).getUserId())).thenReturn(users.get(userNumber));

        mvc.perform(get("/users/" + users.get(userNumber).getUserId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", equalTo(users.get(userNumber).getName())));
    }

    @Test
    public void testGetNotExistingUserById() throws Exception {
        List<User> users = getUsersTestData();
        int userNumber = users.size() - 1;
        when(userService.getById(users.get(userNumber).getUserId())).thenReturn(null);

        mvc.perform(get("/users/" + users.get(userNumber).getUserId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testNewUserCreation() throws Exception {
        User user = getUsersTestData().get(0);
        when(userService.exists(user)).thenReturn(false);
        doNothing().when(userService).create(user);

        mvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user))
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void testExistingUserCreation() throws Exception {
        User user = getUsersTestData().get(0);
        when(userService.exists(user)).thenReturn(true);

        mvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user))
        )
                .andExpect(status().isConflict());
    }

    @Test
    public void testUserUpdate() throws Exception {
        User user = getUsersTestData().get(1);
        when(userService.getById(user.getUserId())).thenReturn(user);

        mvc.perform(
                put("/users/" + user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testNotExistingUserUpdate() throws Exception {
        User user = getUsersTestData().get(1);
        when(userService.getById(user.getUserId())).thenReturn(null);

        mvc.perform(
                put("/users/" + user.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user))
        )
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletingUser() throws Exception {
        User user = getUsersTestData().get(2);
        when(userService.getById(user.getUserId())).thenReturn(user);

        mvc.perform(delete("/users/" + user.getUserId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletingNotExistingUser() throws Exception {
        User user = getUsersTestData().get(2);
        when(userService.getById(user.getUserId())).thenReturn(null);

        mvc.perform(delete("/users/" + user.getUserId()))
                .andExpect(status().isNotFound());
    }
}
