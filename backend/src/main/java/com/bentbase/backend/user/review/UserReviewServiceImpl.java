package com.bentbase.backend.user.review;

import com.bentbase.backend.buyer.Buyer;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.review.Review;
import com.bentbase.backend.review.ReviewService;
import lombok.SneakyThrows;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.Map;
import java.util.Optional;

@Service
public class UserReviewServiceImpl implements UserReviewService {
	
	private final UserReviewRepository userReviewRepository;
	private final ReviewService reviewService;
	
	public UserReviewServiceImpl(UserReviewRepository userReviewRepository, ReviewService reviewService) {
		this.userReviewRepository = userReviewRepository;
		this.reviewService = reviewService;
	}
	
	@SneakyThrows
	@Override
	public UserReview getUserReviewById(UserReviewId userReviewId) {
		Optional<UserReview> review = userReviewRepository.findById(userReviewId);
		if (review.isEmpty()) {
			throw new GetException(UserReview.class).withError("id", "does not exist");
		}
		
		return review.get();
	}
	
	@SneakyThrows
	@Override
	public UserReview createUserReview(UserReview userReview) {
		try {
			return userReviewRepository.save(userReview);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(UserReview.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public UserReview createUserReview(String email, Review review) {
		try {
			if (userReviewRepository.alreadyReviewed(email, review.getReviewer())) {
				String cause = String.format("%s has already reviewed %s", review.getReviewer(), email);
				throw new CreateException(UserReview.class).withError("reviewer", cause);
			}
			
			Review createdReview = reviewService.createReview(review);
			UserReview userReview = new UserReview(new UserReviewId(email, createdReview.getId()));
			return this.createUserReview(userReview);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(UserReview.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public UserReview updateUserReview(Map<String, Object> properties) {
		if (!properties.containsKey("reviewed")) {
			throw new UpdateException(UserReview.class).withError("reviewed", "must not be blank");
		}
		
		var reviewed = String.valueOf(properties.get("reviewed"));
		properties.remove("reviewed");
		var review = reviewService.updateReview(properties);
		return new UserReview(new UserReviewId(reviewed, review.getId()));
	}
	
	@Override
	public void deleteUserReviewById(UserReviewId userReviewId) {
		this.getUserReviewById(userReviewId);
		reviewService.deleteReviewById(userReviewId.getReviewId());
		userReviewRepository.deleteById(userReviewId);
	}
}
