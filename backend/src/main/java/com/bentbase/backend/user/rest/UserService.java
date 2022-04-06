package com.bentbase.backend.user.rest;

import com.bentbase.backend.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
	
	Page<User> getAllUsers(int page, int size, String[] sorts);
	
	User getUserByEmail(String email);
	
	User createUser(User user);
	
	User updateUser(Map<String, Object> properties);
	
	void deleteUserByEmail(String email);
	
	void postReview(Review review);
}
