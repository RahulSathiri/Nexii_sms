package com.omniwyse.sms.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.dieselpoint.norm.Database;
import com.omniwyse.sms.db.DBFactory;
import com.omniwyse.sms.models.Tenants;

public class BaseService {

    @Autowired
    private DBFactory dbFactory;

    protected Database getSmsDB() {
        return dbFactory.getSchoolDb();
    }

    protected Database db(Tenants tenant) {
        return dbFactory.db(tenant);
    }

    public <T> T getById(long id, Tenants tenant, Class<T> clazz) {
        return db(tenant).where("id=?", id).first(clazz);
    }

}
