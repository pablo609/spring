package com.apress.isf.spring.service;

public interface Login {
    boolean isAuthorized(String email, String password);
}
