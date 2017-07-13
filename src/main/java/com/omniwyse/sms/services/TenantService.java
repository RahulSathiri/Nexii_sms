package com.omniwyse.sms.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.omniwyse.sms.models.Tenants;

@Service
public class TenantService extends BaseService {

    public List<Tenants> getAllTenants() {
        return getSmsDB().results(Tenants.class);
    }

    public int saveSchool(Tenants school) {
        return getSmsDB().insert(school).getRowsAffected();
    }

}
