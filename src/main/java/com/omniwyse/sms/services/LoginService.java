package com.omniwyse.sms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DBFactory;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Clients;
import com.omniwyse.sms.models.Parents;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.models.UserCredentials;
import com.omniwyse.sms.models.UserRoleMaintain;
import com.omniwyse.sms.models.UserRoles;
import com.omniwyse.sms.utils.LoginResponse;

@Service
public class LoginService {

    @Autowired
    private DBFactory database;

    private Database db;

    @Autowired
    private DatabaseRetrieval retrive;

    @Autowired
    private LoginResponse response;

    public LoginResponse getUser(Clients clients, long tenantId) {
        db = retrive.getDatabase(tenantId);
        UserCredentials user = db.where("mail=? and password=?", clients.getEmailid(), clients.getPassword()).results(UserCredentials.class).get(0);
        long userroleid = db.where("userid=?", user.getId()).results(UserRoleMaintain.class).get(0).getRoleid();
        String role = db.where("id=?", userroleid).results(UserRoles.class).get(0).getRole();

        if (role.equalsIgnoreCase("ADMIN")) {

        } else if (role.equalsIgnoreCase("TEACHER")) {

            response.setUserId(db.where("emailid = ?", clients.getEmailid()).results(Teachers.class).get(0).getId());

        } else if (role.equalsIgnoreCase("PARENT")) {

            response.setUserId(db.where("emailid = ?", clients.getEmailid()).results(Parents.class).get(0).getId());

        } else if (role.equalsIgnoreCase("STUDENT")) {

        }
        response.setUserrole(role);
        response.setUsername(user.getMail());
        return response;
    }
}
