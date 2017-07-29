package com.omniwyse.sms.utils;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class LoginResponse {

    private long status;
    private String description;
    private long tenantId;
    private String tenantName;
    private Date dateofestblishment;
    private long tenantstatus;
    private UserAndRoles userandroles;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Date getDateofestblishment() {
        return dateofestblishment;
    }

    public void setDateofestblishment(Date dateofestblishment) {
        this.dateofestblishment = dateofestblishment;
    }

    public long getTenantstatus() {
        return tenantstatus;
    }

    public void setTenantstatus(long tenantstatus) {
        this.tenantstatus = tenantstatus;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserAndRoles getUserandroles() {
        return userandroles;
    }

    public void setUserandroles(UserAndRoles userandroles) {
        this.userandroles = userandroles;
    }

}
