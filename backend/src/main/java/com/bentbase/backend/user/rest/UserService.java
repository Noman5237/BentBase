package com.bentbase.backend.user.rest;

import com.bentbase.backend.user.exception.UserCreationException;
import com.bentbase.backend.utils.SortUtil;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

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
	
	@SneakyThrows
	public User createUser(User user) {
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser.isPresent()) {
//			throw new UserCreationException()
		}
		try {
			return userRepository.save(user);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new UserCreationException(exception);
		}
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	public void deleteUserByEmail(String email) {
		userRepository.deleteUserByEmail(email);
	}
}
