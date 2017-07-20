package com.omniwyse.sms.utils;

import org.springframework.stereotype.Service;

import com.omniwyse.sms.models.UserCredentials;

@Service
public class UserRoles {

    private long userroleid;
    private UserCredentials user;
    private String role;

    public long getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(long userroleid) {
        this.userroleid = userroleid;
    }

    public UserCredentials getUser() {
        return user;
    }

    public void setUser(UserCredentials user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
