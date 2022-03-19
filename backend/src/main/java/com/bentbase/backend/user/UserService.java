package com.bentbase.backend.user;

import com.bentbase.backend.utils.SortUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Page<User> getAllUsers(int page, int size, String[] sorts) {
		PageRequest pagingSort = PageRequest.of(page, size, SortUtil.getOrdersFromStringArray(sorts));
		return userRepository.findAll(pagingSort);
	}
	
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user.orElse(null);
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	public void deleteUserByEmail(String email) {
		userRepository.deleteUserByEmail(email);
	}
}
