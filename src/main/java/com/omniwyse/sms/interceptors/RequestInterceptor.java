package com.omniwyse.sms.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.omniwyse.sms.models.Tenants;
import com.omniwyse.sms.services.TenantService;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    TenantService tenantService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getServletPath().startsWith("/health") || request.getServletPath().startsWith("/tenants")) {
            return true;
        }
        String tenantStr = request.getHeader("tenant");
        if (StringUtils.isBlank(tenantStr)) {
            throw new RuntimeException("Tenant not specified");
        } else {
            Tenants tenant = tenantService.getTenant(tenantStr);
            if (tenant == null || tenant.getStatusid() != 1) {
                throw new RuntimeException("Requested tenant [" + tenantStr + "] is not supported yet!!!");
            }
            request.setAttribute("tenant", tenant);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
