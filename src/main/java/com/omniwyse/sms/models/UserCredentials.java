package com.omniwyse.sms.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "usercredentials")
public class UserCredentials {
    private long id;
    private long statusid;
    private String mail;
    private String password;
    private Set<Roles> userRole = new HashSet<Roles>(0);

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStatusid() {
        return statusid;
    }

    public void setStatusid(long statusid) {
        this.statusid = statusid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<Roles> userRole) {
        this.userRole = userRole;
    }

}
