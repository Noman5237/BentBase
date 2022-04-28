package com.bentbase.backend.user.review;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "user_review")
public class UserReview {
	
	@EmbeddedId
	private UserReviewId id;
}