package com.omniwyse.sms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.omniwyse.sms.models.UserCredentials;

@Service
public class SecurityService {

    @Autowired
    private MyUserDetailsService userdetailsservice;

    @Autowired
    public AuthenticationManager uthentication;

    public User getUser(String username, String password) {

        UserCredentials user = (UserCredentials) userdetailsservice.loadUserByUsername(username);
        return null;
    }

}
