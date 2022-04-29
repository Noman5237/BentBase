package com.bentbase.backend.user.review;

import com.bentbase.backend.core.exception.constraint.Message;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.review.Review;
import com.bentbase.backend.review.ReviewService;
import com.bentbase.backend.seller.Seller;
import lombok.SneakyThrows;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping ("/userReviews")
public class UserReviewController {
	
	private final UserReviewService userReviewService;
	private final ReviewService reviewService;
	
	public UserReviewController(UserReviewService userReviewService, ReviewService reviewService) {
		this.userReviewService = userReviewService;
		this.reviewService = reviewService;
	}
	
	@PostMapping ("/create")
	public UserReviewDTO createUserReview(@RequestBody @Validated UserReviewDTO userReviewDTO) {
		var userReview = this.userReviewService.createUserReview(userReviewDTO.getReviewed(),
		                                                         userReviewDTO.getReview());
		var review = reviewService.getReviewById(userReview.getId()
		                                                   .getReviewId());
		return new UserReviewDTO(userReview.getId()
		                                   .getUserEmail(), review);
	}
	
	@SneakyThrows
	@PatchMapping ("/update")
	public UserReviewDTO updateUserReview(@RequestBody Map<String, Object> properties) {
		var userReview = userReviewService.updateUserReview(properties);
		
		var review = reviewService.getReviewById(userReview.getId()
		                                                   .getReviewId());
		return new UserReviewDTO(userReview.getId()
		                                   .getUserEmail(), review);
	}
	
	@DeleteMapping ()
	public String deleteUserReviewById(@RequestBody UserReviewId id) {
		userReviewService.deleteUserReviewById(id);
		return String.format("userReview with id: `%s` is deleted", id);
	}
}
