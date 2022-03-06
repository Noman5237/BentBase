package com.bentbase.backend.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user.orElse(null);
	}
}
