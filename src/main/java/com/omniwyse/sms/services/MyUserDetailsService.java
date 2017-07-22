package com.omniwyse.sms.services;

import java.util.HashSet;
import java.util.List;
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
import com.omniwyse.sms.models.UserRoleMaintain;
import com.omniwyse.sms.models.UserRoles;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private DatabaseRetrieval retrive;

    private Database db;

    @SuppressWarnings("unused")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        db = retrive.getDatabase(1);
        List<UserCredentials> userlist = db.where("mail=?", username).results(UserCredentials.class);
        List<UserRoleMaintain> roles = null;
        UserCredentials mainuser = null;
        for (UserCredentials user : userlist) {
            mainuser = user;
            roles = db.where("userid=?", user.getId()).results(UserRoleMaintain.class);
        }
        if (userlist == null) {
            throw new UsernameNotFoundException("User not found/exist");
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (UserRoleMaintain role : roles) {
                List<UserRoles> assignedroleslist = db.where("id=?", role.getRoleid()).results(UserRoles.class);
                String assignedrole = null;
                for (UserRoles assignedroles : assignedroleslist) {
                    assignedrole = assignedroles.getRole();
                    grantedAuthorities.add(new SimpleGrantedAuthority(assignedrole));
                }
            }
            return new org.springframework.security.core.userdetails.User(mainuser.getMail(), mainuser.getPassword(),grantedAuthorities);
        }

    }
}
