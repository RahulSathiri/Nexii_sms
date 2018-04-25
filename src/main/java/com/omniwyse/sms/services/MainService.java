package com.omniwyse.sms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DBFactory;
import com.omniwyse.sms.models.Tenants;

@Service
public class MainService {

    @Autowired
    DBFactory database;

    public Database db;

    public Tenants getTenant(String user) {
        db = database.getSchoolDb();
        return db.where("url=?", user).results(Tenants.class).get(0);
    }

}
