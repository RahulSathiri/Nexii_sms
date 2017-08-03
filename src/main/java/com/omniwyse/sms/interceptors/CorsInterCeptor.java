package com.omniwyse.sms.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CorsInterCeptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!response.containsHeader("Access-Control-Allow-Origin")) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");
            response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, tenant, caller");
            response.addHeader("Access-Control-Request-Headers", "Authorization, Content-Type, Accept, tenant, caller");
            response.addHeader("Access-Control-Max-Age", "86400");
        }
        return !request.getMethod().equalsIgnoreCase(RequestMethod.OPTIONS.name());
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
