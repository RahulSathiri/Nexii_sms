package com.omniwyse.sms.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DBFactory;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Clients;
import com.omniwyse.sms.models.Parents;
import com.omniwyse.sms.models.Teachers;
import com.omniwyse.sms.models.Tenants;
import com.omniwyse.sms.models.UserCredentials;
import com.omniwyse.sms.models.UserRoleMaintain;
import com.omniwyse.sms.models.UserRoles;
import com.omniwyse.sms.utils.LoginResponse;
import com.omniwyse.sms.utils.UserAndRoles;

@Service
public class LoginService {

    @Autowired
    private DBFactory database;

    private Database db;

    @Autowired
    private DatabaseRetrieval retrive;

    @Autowired
    private LoginResponse response;

    public UserCredentials getUser(Clients clients, long tenantId) {
        db = retrive.getDatabase(tenantId);
        UserCredentials user = db.where("mail=? and password=?", clients.getEmailid(), clients.getPassword()).results(UserCredentials.class).get(0);
        List<Teachers> teacherlist = db.where("emailid = ?", clients.getEmailid()).results(Teachers.class);
        List<Parents> parentslist = db.where("emailid = ?", clients.getEmailid()).results(Parents.class);
        if (!teacherlist.isEmpty()) {
            for (Teachers teacher : teacherlist) {
                user.setId(teacher.getId());
            }
        } else if (!parentslist.isEmpty()) {
            for (Parents parent : parentslist) {
                user.setId(parent.getId());
            }
        }
        return user;
    }

    public ResponseEntity<LoginResponse> userLogin(UserCredentials clients, long tenantId) {
        db = retrive.getDatabase(tenantId);
        List<UserCredentials> userlist = db.where("mail=? and password=?", clients.getMail(), clients.getPassword()).results(UserCredentials.class);

        if (userlist.isEmpty()) {
            response.setStatus(400);
            response.setDescription("failed");
            return new ResponseEntity<LoginResponse>(response, HttpStatus.BAD_REQUEST);
        } else {

            UserAndRoles userandroles = new UserAndRoles();
            List<UserRoleMaintain> roles = null;
            UserCredentials mainuser = null;
            List<UserRoles> assignedroleslist = null;

            for (UserCredentials user : userlist) {
                mainuser = user;
                roles = db.where("userid=?", user.getId()).results(UserRoleMaintain.class);
            }

            for (UserRoleMaintain role : roles) {
                assignedroleslist = db.where("id=?", role.getRoleid()).results(UserRoles.class);
            }
            Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
            if (mainuser != null && mainuser.getMail() != null) {
                userandroles.setUserid(mainuser.getId());
                userandroles.setUsername(mainuser.getMail());
                userandroles.setUserstatus(mainuser.getStatusid());
                String assignedrole = null;
                for (UserRoles assignedroles : assignedroleslist) {
                    assignedrole = assignedroles.getRole();
                    grantedAuthorities.add(new SimpleGrantedAuthority(assignedrole));
                }
                if (!grantedAuthorities.isEmpty() && grantedAuthorities != null) {
                    userandroles.setRoles(grantedAuthorities);
                }
            }
            long id = tenantId;
            Tenants tenant = database.getSchoolDb().where("id=?", id).results(Tenants.class).get(0);
            response.setStatus(200);
            response.setDescription("success");
            // response.setTenantId(tenantId);
            // response.setTenantName(tenant.getSname());
            // response.setDateofestblishment(tenant.getDateofestablishment());
            // response.setTenantstatus(tenant.getStatusid());
            // response.setUserandroles(userandroles);
            return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
        }
    }
}
