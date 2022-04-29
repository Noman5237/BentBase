package com.bentbase.backend.user.review;

import com.bentbase.backend.core.exception.constraint.Message;
import com.bentbase.backend.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserReviewDTO {
	
	@NotBlank
	@Email (message = Message.EMAIL)
	private String reviewed;
	
	private Long reviewId;
	
	private float rating;
	
	@NotBlank
	private String comments;
	
	@NotBlank
	@Email (message = Message.EMAIL)
	private String reviewer;
	
	public UserReviewDTO(String reviewed, Review review) {
		this.reviewed = reviewed;
		this.reviewId = review.getId();
		this.rating = review.getRating();
		this.comments = review.getComments();
		this.reviewer = review.getReviewer();
	}
	
	@JsonIgnore
	public Review getReview() {
		return new Review(reviewId, rating, comments, reviewer);
	}
}
