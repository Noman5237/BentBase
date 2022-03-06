package com.bentbase.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Override
	List<User> findAll();
	
	Optional<User> findByEmail(String email);
	
}
