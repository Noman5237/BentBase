package com.bentbase.backend.user.review;

import com.bentbase.backend.review.Review;
import com.bentbase.backend.review.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/userReviews")
public class UserReviewController {
	
	private final UserReviewService userReviewService;
	
	public UserReviewController(UserReviewService userReviewService) {
		this.userReviewService = userReviewService;
	}
	
	@PostMapping ("/post")
	public UserReview createUserReview(@RequestParam String email, @RequestBody Review review) {
		return this.userReviewService.createUserReview(email, review);
	}
	
	@DeleteMapping ()
	public String deleteUserReviewById(@RequestBody UserReviewId id) {
		userReviewService.deleteUserReviewById(id);
		return String.format("userReview with id: `%s` is deleted", id);
	}
}
