package com.omniwyse.sms.utils;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class UserAndRoles {

    private long userid;
    private String username;
    private long userstatus;
    private Set<GrantedAuthority> roles;



    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(long userstatus) {
        this.userstatus = userstatus;
    }

    public Set<GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<GrantedAuthority> roles) {
        this.roles = roles;
    }

}
