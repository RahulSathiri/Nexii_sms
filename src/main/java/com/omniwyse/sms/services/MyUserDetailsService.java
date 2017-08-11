package com.omniwyse.sms.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.config.RequestInfo;
import com.omniwyse.sms.db.DBFactory;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Tenants;
import com.omniwyse.sms.models.UserCredentials;
import com.omniwyse.sms.models.UserRoleMaintain;
import com.omniwyse.sms.models.UserRoles;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private DatabaseRetrieval retrive;

    @Autowired
    private RequestInfo info;

    @Autowired
    DBFactory database;

    private Database db;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        db = database.getSchoolDb();
        String tenantName = info.getTenantname();
        long tenantId = db.where("dbname=?", tenantName).results(Tenants.class).get(0).getId();
        db = retrive.getDatabase(tenantId);
        List<UserCredentials> userlist = db.where("mail=?", username).results(UserCredentials.class);
        List<UserRoleMaintain> roles = null;
        UserCredentials mainuser = null;
        for (UserCredentials user : userlist) {
            mainuser = user;
            roles = db.where("userid=?", user.getId()).results(UserRoleMaintain.class);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRoleMaintain role : roles) {
            List<UserRoles> assignedroleslist = db.where("id=?", role.getRoleid()).results(UserRoles.class);
            String assignedrole = null;
            for (UserRoles assignedroles : assignedroleslist) {
                assignedrole = assignedroles.getRole();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+assignedrole));
            }
        }
        UserDetails userdetails = new User(mainuser.getMail(), mainuser.getPassword(), true, true, true, true, grantedAuthorities);
        return userdetails;
    }
}
