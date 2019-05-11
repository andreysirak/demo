package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.dao.UserServiceHibernate;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserServiceHibernate userServiceHibernate;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin().and()
		.httpBasic();
		}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth)	throws Exception {
	
		auth.userDetailsService(userServiceHibernate);

	}
}
