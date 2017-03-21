package com.memorynotfound.model;

public class User {
    private String userId;
    private String name;
    private String nickName;

    public User(String userId, String name, String nickName) {
        this.userId = userId;
        this.name = name;
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
