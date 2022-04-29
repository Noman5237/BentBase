package com.bentbase.backend.user.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, UserReviewId>, JpaSpecificationExecutor<UserReview> {
	
	@Query ("select case when count(user_review) > 0 then true else false end from UserReview user_review join Review review on user_review.id.reviewId = review.id where user_review.id.userEmail = :reviewed and review.reviewer = :reviewer")
	boolean alreadyReviewed(@Param ("reviewed") String reviewed, @Param ("reviewer") String reviewer);
	
	@Override
	void deleteById(UserReviewId userReviewId);
}