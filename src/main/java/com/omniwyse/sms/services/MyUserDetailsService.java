package com.omniwyse.sms.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.UserCredentials;
import com.omniwyse.sms.models.UserRoles;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private DatabaseRetrieval retrive;

    private Database db;

    // @Transactional
    // public UserDetails loadUserByCredentials(String username, String
    // password) {
    // db = retrive.getDatabase(1);
    // UserCredentials user = (UserCredentials)
    // db.where("mail=? and password=?", username,
    // password).results(UserCredentials.class);
    // Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    // for (UserRoles role : user.getUserRole()) {
    // grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
    // }
    // return new
    // org.springframework.security.core.userdetails.User(user.getMail(),
    // user.getPassword(), grantedAuthorities);
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        db = retrive.getDatabase(1);
        UserCredentials user = (UserCredentials) db.where("mail=?", username).results(UserCredentials.class);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRoles role : user.getUserRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), grantedAuthorities);
    }
}
