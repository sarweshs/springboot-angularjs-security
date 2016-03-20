package com.sarwesh.springboot.init;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sarwesh.springboot.model.Roles;
import com.sarwesh.springboot.model.User;
import com.sarwesh.springboot.repository.UserRepository;

@Component
public class MyAuthInitializer {
	@Autowired
	UserRepository userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        User user = new User();
        user.setUserName("admin");
        user.setAddress("Test");
        //user.setAddress("Test");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setStatus("ACTIVE");
        Set<Roles> roles = new HashSet<>();
        Roles role = new Roles();
        role.setRoleName("ROLE_ADMIN");
        role.setId(1l);
		roles.add(role);
		user.setRoles(roles);
		userService.save(user);
		System.out.println("User saved:" + user);
    }
}
