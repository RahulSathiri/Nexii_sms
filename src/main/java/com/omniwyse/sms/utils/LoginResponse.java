package com.omniwyse.sms.utils;

import org.springframework.stereotype.Service;

@Service
public class LoginResponse {

    private long status;
    private String description;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
