package com.bentbase.backend.user.rest;

import com.bentbase.backend.core.exception.FallbackExceptionHandler;
import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.user.exception.UserCreateException;
import com.bentbase.backend.user.exception.UserGetException;
import com.bentbase.backend.user.exception.UserUpdateException;
import com.bentbase.backend.utils.PatchUtil;
import com.bentbase.backend.utils.SortUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(FallbackExceptionHandler.class);
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Page<User> getAllUsers(int page, int size, String[] sorts) {
		PageRequest pagingSort = PageRequest.of(page, size, SortUtil.getOrdersFromStringArray(sorts));
		return userRepository.findAll(pagingSort);
	}
	
	@SneakyThrows
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isEmpty()) {
			throw new UserGetException().withError("email", "does not exist");
		}
		
		return user.get();
	}
	
	@SneakyThrows
	public User createUser(User user) {
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser.isPresent()) {
			throw new UserCreateException().withError("email", "already exists");
		}
		
		try {
			return userRepository.save(user);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new UserCreateException(exception);
		}
	}
	
	@SneakyThrows
	public User updateUser(Map<String, Object> properties) {
		if (!properties.containsKey("email")) {
			throw new UserGetException().withError("email", "must not be blank");
		}
		
		var email = (String) properties.get("email");
		var user = this.getUserByEmail(email);
		
		try {
			PatchUtil.update(user, properties);
		} catch (RESTException exception) {
			throw new UserUpdateException(exception);
		}
		
		return userRepository.save(user);
	}
	
	public void deleteUserByEmail(String email) {
		this.getUserByEmail(email);
		userRepository.deleteUserByEmail(email);
	}
}
