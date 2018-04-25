package com.omniwyse.sms.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.Transaction;
import com.omniwyse.sms.Application;
import com.omniwyse.sms.db.DBFactory;
import com.omniwyse.sms.models.Clients;
import com.omniwyse.sms.models.Tenants;
import com.omniwyse.sms.utils.Users;

@Service
public class RegistrationService {

	@Autowired
	private DBFactory database;

    final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public int a, b;

	private String code;

	private String emailid;

	private Database db;

	private final Tenants tenant;

	private final Clients client;

	public RegistrationService() {
		tenant = new Tenants();
		client = new Clients();
	}

	public int registration(Users users) {

		tenant.setSname(users.getSchoolname());
        // tenant.setLocation(users.getCity() + "," + users.getStreet());
		code = users.getSchoolcode();
		tenant.setCode(code);
		tenant.setDbname(users.getSchoolcode());
		tenant.setDateofestablishment(users.getDateofestablishment());
		tenant.setUrl("iSchool"+users.getSchoolname());
		client.setFname(users.getContactname());
		client.setContactnumber(users.getContactnumber());
		client.setEmailid(users.getEmailid()); 
		client.setPassword(users.getPassword());
		db = database.getSchoolDb();
		Transaction transact = db.startTransaction();
		try{
		if (isValidCode()) {
			if (isValidEmailId()) {
				a = db.transaction(transact).insert(tenant).getRowsAffected();
				client.setSchoolid(tenant.getId());
				b = db.transaction(transact).insert(client).getRowsAffected();
				transact.commit();
			} else
				return -1;
		} else
			return -1;
		}catch (Exception e) {
			transact.rollback();
		}
		return b;

	}

	private boolean isValidEmailId() {
		List<Clients> data = db.sql("select emailid from clients").results(Clients.class);
		for (Clients p : data) {
			LOGGER.info("emailid" + p.getEmailid());
			if (p.getEmailid().equals(emailid)) {
				return false;
			}
		}
		return true;

	}

	private boolean isValidCode() {
		List<Tenants> data = db.sql("select code from schools").results(Tenants.class);
		for (Tenants codes : data) {
			LOGGER.info("CODE" + codes.getCode());
			if (codes.getCode().equals(code)) {
				return false;
			}
		}
		return true;
	}
}