package com.apress.isf.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements Login {
    @Value("${user.login}")
    private String login;
    @Value("${user.password}")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAuthorized(String email, String password) {
        if(this.login.equals(email) && this.password.equals(password))
            return true;

        return false;
    }
}
