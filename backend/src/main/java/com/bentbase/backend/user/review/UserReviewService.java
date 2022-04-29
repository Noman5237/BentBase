package com.bentbase.backend.user.review;

import com.bentbase.backend.review.Review;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserReviewService {
	
	UserReview getUserReviewById(UserReviewId userReviewId);
	
	UserReview createUserReview(UserReview userReview);
	
	UserReview createUserReview(String email, Review review);
	
	UserReview updateUserReview(Map<String, Object> properties);
	
	void deleteUserReviewById(UserReviewId userReviewId);
	
}
