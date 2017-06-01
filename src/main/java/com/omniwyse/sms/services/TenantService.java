package com.omniwyse.sms.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.omniwyse.sms.models.Tenant;

@Service
public class TenantService extends BaseService {

    public void save(Tenant tenant) {

    }

    public List<Tenant> findSchools() {
        return getSmsDB().results(Tenant.class);
    }

}
