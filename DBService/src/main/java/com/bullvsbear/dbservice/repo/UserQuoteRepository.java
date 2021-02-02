package com.bullvsbear.dbservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bullvsbear.dbservice.entity.UserQuote;

public interface UserQuoteRepository extends JpaRepository<UserQuote, Long> {
	
	List<UserQuote> findByUserId(Long userId);
}
