package com.omniwyse.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.omniwyse.sms.utils.MyAccessDeniedHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;
    
    @Autowired
    private MyAccessDeniedHandler accesshandler;

    @Override
    protected void configure(HttpSecurity httpsecurity) {
        try {
            httpsecurity.csrf().disable();
            httpsecurity.authorizeRequests().antMatchers("/admin**").authenticated().anyRequest().permitAll().and()
                    .formLogin().permitAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // try {
        // httpsecurity.authorizeRequests().antMatchers("/", "/home", "/about",
        // "/superadmin").permitAll()
        // .anyRequest().authenticated().antMatchers("**/admin/**").access("hasRole('ADMIN')")
        // // hasAnyRole("SUPERADMIN").antMatchers("/admin/**")
        // //
        // .hasAnyRole("ADMIN").antMatchers("/teacher/**").hasAnyRole("TEACHER").antMatchers("/parent/**")
        // //
        // .hasAnyRole("PARENT").antMatchers("/student/**").hasAnyRole("STUDENT").anyRequest().authenticated()
        // .and().formLogin().permitAll().and().logout().permitAll().and()
        // .exceptionHandling().accessDeniedHandler(accesshandler).and().csrf();
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        //
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
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
