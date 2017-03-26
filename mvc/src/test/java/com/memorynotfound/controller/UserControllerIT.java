package com.memorynotfound.controller;

import com.memorynotfound.config.WebConfig;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class UserControllerIT {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testConfiguration() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("userController"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(Arrays.asList("User 1", "User 2"));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].userId", equalTo("1")))
                .andExpect(jsonPath("$[*].name", equalTo(jsonArray)));
    }

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/users/{userId}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", equalTo("User 1")));
    }

    @Test
    public void testUnknownGetUser() throws Exception {
        mockMvc.perform(get("/users/{userId}", "xxx"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUserCreation() throws Exception {
        Map<String, String> user = new HashMap<>();
        final String USER_NAME = "Nowy User";
        user.put("name", USER_NAME);
        user.put("nickName", "Nick Name");

        String newUserURL = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONObject.toJSONString(user))
        )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader(HttpHeaders.LOCATION);

        mockMvc.perform(get(newUserURL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", equalTo(USER_NAME)));
    }

    @Test
    public void testUserWithExistingNameCreation() throws Exception {
        Map<String, String> user = new HashMap<>();
        final String USER_NAME = "Existing User";
        user.put("name", USER_NAME);
        user.put("nickName", "Nick Name");

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONObject.toJSONString(user))
        )
                .andExpect(status().isCreated());

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONObject.toJSONString(user))
        )
                .andExpect(status().isConflict());

    }

    @Test
    public void testUserDeletion() throws Exception {
        Map<String, String> user = new HashMap<>();
        final String USER_NAME = "Delete User";
        user.put("name", USER_NAME);
        user.put("nickName", "Nick Name");

        String userURL = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONObject.toJSONString(user))
        )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getHeader(HttpHeaders.LOCATION);

        mockMvc.perform(delete(userURL))
                .andExpect(status().isOk());

        mockMvc.perform(get(userURL))
                .andExpect(status().isNotFound());
    }
}
