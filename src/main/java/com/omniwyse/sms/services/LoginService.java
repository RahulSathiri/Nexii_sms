package com.omniwyse.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DBFactory;
import com.omniwyse.sms.models.Clients;
import com.omniwyse.sms.models.Tenants;
import com.omniwyse.sms.utils.LoginResponse;

@Service
public class LoginService {

	@Autowired
	private DBFactory database;
	private Database db;
	private String emailid;
	private String password;
	private String sname;
	private long id;

	@Autowired
	private LoginResponse response;

	public ResponseEntity<LoginResponse> userLogin(Clients clients) {
		emailid = clients.getEmailid();
		password = clients.getPassword();
		db = database.getSchoolDb();
		List<Clients> result = db.where("emailid=? and password=?", emailid, password).results(Clients.class);

		if (result.isEmpty()) {
			response.setStatus(400);
			response.setId(0);
			response.setDescription("failed");

			return new ResponseEntity<LoginResponse>(response, HttpStatus.BAD_REQUEST);

		} else {
			id = result.get(0).getSchoolid();
			sname = db.where("id=?", id).results(Tenants.class).get(0).getSname();
			response.setStatus(200);
			response.setId(id);
			response.setDescription("success");
			response.setSchoolname(sname);
			return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
		}
	}
}
