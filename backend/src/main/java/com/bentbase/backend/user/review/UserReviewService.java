package com.bentbase.backend.user.review;

import com.bentbase.backend.review.Review;
import org.springframework.stereotype.Service;

@Service
public interface UserReviewService {
	
	UserReview createUserReview(UserReview userReview);
	
	UserReview createUserReview(String email, Review review);
	
	void deleteUserReviewById(UserReviewId userReviewId);
	
}
