package com.omniwyse.sms.utils;

import java.util.Set;

import com.omniwyse.sms.models.UserCredentials;
import com.omniwyse.sms.models.UserRoles;

public class UserAndRoles {

    private UserCredentials user;
    private Set<UserRoles> roles;

    public UserCredentials getUser() {
        return user;
    }

    public void setUser(UserCredentials user) {
        this.user = user;
    }

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }
}
