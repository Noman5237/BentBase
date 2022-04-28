package com.bentbase.backend.review;

import org.springframework.data.domain.Page;

import static com.bentbase.backend.utils.PageUtil.Paginate;

public interface ReviewService {
	
	Page<Review> getAllReviews(Paginate paginate);
	
	Review getReviewById(Long id);
	
	Review createReview(Review review);
	
	Review updateReview(Review review);
	
	void deleteReviewById(String id);
}
