package com.bentbase.backend.user.review;

import com.bentbase.backend.review.Review;
import com.bentbase.backend.review.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class UserReviewServiceImpl implements UserReviewService {
	
	private final UserReviewRepository userReviewRepository;
	private final ReviewService reviewService;
	
	public UserReviewServiceImpl(UserReviewRepository userReviewRepository,
	                             ReviewService reviewService) {
		this.userReviewRepository = userReviewRepository;
		this.reviewService = reviewService;
	}
	
	@Override
	public UserReview createUserReview(UserReview userReview) {
		return userReviewRepository.save(userReview);
	}
	
	@Override
	public UserReview createUserReview(String email, Review review) {
		Review createdReview = reviewService.createReview(review);
		UserReview userReview = new UserReview(new UserReviewId(email, createdReview.getId()))
		return this.createUserReview(userReview);
	}
	
	@Override
	public void deleteUserReviewById(UserReviewId userReviewId) {
		userReviewRepository.deleteById(userReviewId);
	}
}
