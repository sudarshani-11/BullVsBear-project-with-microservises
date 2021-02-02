package com.bullvsbear.dbservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bullvsbear.dbservice.entity.User;

public interface UserRepo extends JpaRepository<User,Long> {
	boolean existsByEmail(String email);
	
	boolean existsByuserName(String userName);
	
	User findByEmail(String email);
}
