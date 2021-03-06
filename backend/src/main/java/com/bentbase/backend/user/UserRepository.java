package com.bentbase.backend.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	
	@Override
	Page<User> findAll(Pageable pageable);
	
	Optional<User> findByEmail(String email);
	
	@Transactional
	void deleteByEmail(String email);
}
