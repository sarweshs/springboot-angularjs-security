package com.sarwesh.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sarwesh.springboot.model.Roles;
import com.sarwesh.springboot.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userService;

    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<com.sarwesh.springboot.model.User> userList= userService.findByUserName(s);

        //check if this user with this username exist, if not, throw an exception
        // and stop the login process
        if (userList == null || userList.isEmpty()) {
            throw new UsernameNotFoundException("User details not found with this username: " + s);
        }
        com.sarwesh.springboot.model.User user = userList.get(0);
        String username = user.getUserName();
        String password = user.getPassword();
        //String role = (String) userMap.get("role");

        List authList = getAuthorities(user.getRoles());

        //get the encoded password
        String encodedPassword = password;//passwordEncoder.encode(password);

        User springUser = new User(username, encodedPassword, authList);

        return springUser;
    }
    
    private List getAuthorities(Set<Roles> roles) {
        List authList = new ArrayList();

        //you can also add different roles here
        //for example, the user is also an admin of the site, then you can add ROLE_ADMIN
        //so that he can view pages that are ROLE_ADMIN specific
        if (roles != null && !roles.isEmpty()) {
        	for(Roles r:roles)
        	authList.add(new SimpleGrantedAuthority(r.getRoleName()));
        }

        return authList;
    }

    private List getAuthorities(String role) {
        List authList = new ArrayList();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        //you can also add different roles here
        //for example, the user is also an admin of the site, then you can add ROLE_ADMIN
        //so that he can view pages that are ROLE_ADMIN specific
        if (role != null && role.trim().length() > 0) {
            if (role.equals("admin")) {
                authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        }

        return authList;
    }
}
