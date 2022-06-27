/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.config;

import mx.com.rotwsservice.authUtils.AuthFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author mvillavicencio
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests() 
//            .antMatchers("/**").permitAll();
//        http.authorizeRequests()
//            .anyRequest().authenticated();
        http.addFilterAfter(
            new AuthFilter(), BasicAuthenticationFilter.class);
    }
}
