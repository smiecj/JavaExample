/* package com.example.restservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", 
        // "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/swagger-ui/**"
        // , "/webjars/**", "/csrf", "/");
        http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/swagger-ui/index.html**", "/webjars/**", "/actuator/health").permitAll().and();
    }
} */