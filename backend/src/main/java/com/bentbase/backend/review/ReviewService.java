package com.bentbase.backend.review;

import org.springframework.data.domain.Page;

import java.util.Map;

import static com.bentbase.backend.utils.PageUtil.Paginate;

public interface ReviewService {
	
	Page<Review> getAllReviews(Paginate paginate);
	
	Review getReviewById(Long id);
	
	Review createReview(Review review);
	
	Review updateReview(Map<String, Object> properties);
	
	void deleteReviewById(Long id);
}
