package com.sarwesh.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarwesh.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUserName(String userName);
	
}
