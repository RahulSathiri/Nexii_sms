package com.omniwyse.sms.services;

import java.util.List;

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
        List<Teachers> teacherlist = db.where("emailid = ?", clients.getEmailid()).results(Teachers.class);
        List<Parents> parentslist = db.where("emailid = ?", clients.getEmailid()).results(Parents.class);
       // List<StudentClassroom> studentslist = db.where("emailid = ?", clients.getEmailid()).results(StudentClassroom.class);
        if (!teacherlist.isEmpty()) {
            for (Teachers teacher : teacherlist) {
                response.setUserId(teacher.getId());
            }
        } else if (!parentslist.isEmpty()) {
            for (Parents parent : parentslist) {
                response.setUserId(parent.getId());
            }
        } else {
            response.setUserId(user.getId());
        }
        response.setUserrole(role);
        response.setUsername(user.getMail());
        return response;
    }
}
