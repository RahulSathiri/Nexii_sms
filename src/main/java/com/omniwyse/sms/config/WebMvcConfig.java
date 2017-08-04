package com.omniwyse.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.omniwyse.sms.interceptors.CorsInterCeptor;
import com.omniwyse.sms.interceptors.RequestInterceptor;
import com.omniwyse.sms.interceptors.SecurityInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestInterceptor reqInterceptor;

    @Autowired
    private SecurityInterceptor secInterceptor;

    @Autowired
    CorsInterCeptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor);
        registry.addInterceptor(reqInterceptor).excludePathPatterns("/tenant/for/**");
        registry.addInterceptor(secInterceptor);
    }

}