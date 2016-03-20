package com.sarwesh.springboot.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sarwesh.springboot.auth.handler.CustomSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired	
	UserDetailsService userDetailsService;
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {			 
	 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());;
		
	}	
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.authorizeRequests()
		//.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")		
		//.anyRequest().permitAll()
	  .anyRequest().authenticated()
		.and()
		  .formLogin().loginPage("/login").permitAll()
		  .usernameParameter("username").passwordParameter("password")
		  //.defaultSuccessUrl(defaultSuccessUrl)
		.and()
		  .logout().logoutSuccessUrl("/login?logout").permitAll()	
		 .and()
		 .exceptionHandling().accessDeniedPage("/403")
		.and()
		  .csrf();
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	  	.antMatchers("/", "/home").access("hasRole('USER')")
	  	.antMatchers("/admin/**").access("hasRole('ADMIN')")
	  	.antMatchers("/admin/user/**").access("hasRole('ADMIN')")
	  	.antMatchers("/user/**").access("hasRole('ADMIN')")
	  	.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
	  	.and().formLogin().loginPage("/login")
	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.successHandler(customSuccessHandler)
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied")
	  	.and().csrf().disable();
	}
	
	@Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
    	return new BCryptPasswordEncoder();
    }
}
