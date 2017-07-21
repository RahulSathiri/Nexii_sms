package com.omniwyse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.omniwyse.sms.services.MyUserDetailsService;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accesshandler;
    
    @Autowired
    MyUserDetailsService myuserDetailsService;

    @Override
    protected void configure(HttpSecurity httpsecurity) {
        
        try {
            httpsecurity.csrf().disable().authorizeRequests().antMatchers("/", "/home", "/about").permitAll()
                    .antMatchers("/superadmin/**").hasAnyRole("SUPERADMIN").antMatchers("/admin/**")
                    .hasAnyRole("ADMIN").antMatchers("/teacher/**").hasAnyRole("TEACHER")
                    .antMatchers("/parent/**").hasAnyRole("PARENT").antMatchers("student/**").hasAnyRole("STUDENT")
                    .anyRequest().authenticated().and().formLogin().permitAll().and().logout()
                    .permitAll().and().exceptionHandling().accessDeniedHandler(accesshandler);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myuserDetailsService);
//        .passwordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence){
//                return charSequence.toString();
//            }
//            @Override
//            public boolean matches(CharSequence charSequence,String s){
//                return true;
//            }
//        });
    }
    
}
