package com.sarwesh.springboot.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
public class AuthenticationProviderConfig {
	//Enable when you need to use mysql
	/*@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/userbase");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("root");
	    return driverManagerDataSource;
	}*/
	@Autowired
	DataSource dataSource;
    
   // @Bean(name="userDetailsService")
    public UserDetailsService userDetailsService(){
    	JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
    	//jdbcImpl.setDataSource(dataSource());
    	jdbcImpl.setDataSource(dataSource);
    	jdbcImpl.setUsersByUsernameQuery("select username,password, status from users where username=? and status='ACTIVE'");
    	jdbcImpl.setAuthoritiesByUsernameQuery("select b.username, c.role from user_roles a, users b,roles c where b.username=? and a.userid=b.userid and b.roleid=c.roleid");
    	return jdbcImpl;
    }
}
