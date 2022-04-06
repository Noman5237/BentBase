package com.bentbase.backend.user.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<User, String> {
	
	@Override
	Page<User> findAll(Pageable pageable);
	
	Optional<User> findByEmail(String email);
	
	@Transactional
	void deleteUserByEmail(String email);
}
