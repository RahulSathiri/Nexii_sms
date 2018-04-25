package com.omniwyse.sms.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "usercredentials")
public class UserCredentials {
    private long id;
    private long statusid;
    private String mail;
    private String password;

    // private Set<UserRoles> userRoles = new HashSet<UserRoles>(0);

    public UserCredentials() {

    }

    public UserCredentials(UserCredentials user) {
        this.id = user.getId();
        this.statusid = user.getStatusid();
        this.mail = user.getMail();
        this.password = user.getPassword();
        // this.userRoles = user.getUserRoles();
    }

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

    // public Set<UserRoles> getUserRoles() {
    // return userRoles;
    // }
    //
    // public void setUserRoles(Set<UserRoles> userRoles) {
    // this.userRoles = userRoles;
    // }


}
