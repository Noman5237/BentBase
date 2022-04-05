package com.bentbase.backend.review;

import com.bentbase.backend.user.rest.User;
import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;

public interface ReviewService {
	
	Page<Review> getAllReviews(PageUtil.Paginate paginate);
	
	Review getReviewById(String id);
	
	Page<Review> getReviewsOf(User user, PageUtil.Paginate paginate);
	
	Review createReview(Review review);
	
	Review updateReview(Review review);
	
	void deleteReviewById(String id);
}
