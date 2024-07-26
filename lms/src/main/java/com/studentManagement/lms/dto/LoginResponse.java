package com.studentManagement.lms.dto;

import com.studentManagement.lms.modal.User;

public class LoginResponse {
    private String token;
    private User user;

    public LoginResponse(String token,User user) {
        this.token = token;
        this.user = user;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}