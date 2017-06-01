package com.omniwyse.sms.models;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "schools")
public class Tenant extends BaseModel {
    
    private String name;
    private String code;
    private String dbname;
    private String timezone;

    // private boolean active;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone() {
        return timezone;
    }

    @JsonIgnore
    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    // public void setIsActive(boolean active) {
    // this.active = active;
    // }
    //
    // public boolean isActive() {
    // return active;
    // }
}
