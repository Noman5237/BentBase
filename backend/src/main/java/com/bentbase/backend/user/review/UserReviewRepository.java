package com.bentbase.backend.user.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, UserReviewId>, JpaSpecificationExecutor<UserReview> {
	
	@Override
	void deleteById(UserReviewId userReviewId);
}