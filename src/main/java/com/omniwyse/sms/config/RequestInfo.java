package com.omniwyse.sms.config;

import org.springframework.stereotype.Component;

@Component
public class RequestInfo {

    public static final ThreadLocal<Long> tenantId = new ThreadLocal<Long>();

    public static final ThreadLocal<String> tenantName = new ThreadLocal<String>();

    public static Long getTenantid() {
        return tenantId.get();
    }

    public static String getTenantname() {
        return tenantName.get();
    }

}
