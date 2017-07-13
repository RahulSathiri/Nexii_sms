package com.omniwyse.sms.services;

import java.util.List;

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

    public Long getTenant(String user) {
        db = database.getSchoolDb();
        List<Tenants> tenants = db.where("url=?", user).results(Tenants.class);
        for (Tenants currenttenant : tenants) {
            return currenttenant.getId();
        }
        return 0l;
    }

}
