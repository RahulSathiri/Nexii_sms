package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DBFactory;
import com.omniwyse.sms.db.DatabaseRetrieval;
import com.omniwyse.sms.models.Tenants;
import com.omniwyse.sms.models.UserCredentials;
import com.omniwyse.sms.utils.LoginResponse;

@Service
public class LoginService {

	@Autowired
	private DBFactory database;
	private Database db;

    @Autowired
    private DatabaseRetrieval retrive;

	private String emailid;
	private String password;
	private String sname;
	private long id;

	@Autowired
	private LoginResponse response;

    public ResponseEntity<LoginResponse> userLogin(UserCredentials clients, long tenantId) {
        emailid = clients.getMail();
        password = clients.getPassword();
        db = retrive.getDatabase(tenantId);
        List<UserCredentials> result = db.where("mail=? and password=?", emailid, password).results(
                UserCredentials.class);

        if (result.isEmpty()) {
            response.setStatus(400);
            response.setId(0);
            response.setDescription("failed");
            return new ResponseEntity<LoginResponse>(response, HttpStatus.BAD_REQUEST);
        } else {
            // id = result.get(0).getSchoolid();
            id = tenantId;
            sname = database.getSchoolDb().where("id=?", id).results(Tenants.class).get(0).getSname();
            response.setStatus(200);
            response.setId(id);
            response.setDescription("success");
            response.setSchoolname(sname);
            return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
        }
    }
}
