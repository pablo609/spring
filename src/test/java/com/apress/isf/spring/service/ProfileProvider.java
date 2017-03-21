package com.apress.isf.spring.service;

import org.springframework.test.annotation.ProfileValueSource;

public class ProfileProvider implements ProfileValueSource {
    @Override
    public String get(String key) {
        if(key.equals("phase"))
            return "beta";

        return null;
    }
}
