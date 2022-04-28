package com.bentbase.backend.user;

import com.bentbase.backend.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	
	@Override
	Page<User> findAll(Pageable pageable);
	
	Optional<User> findByEmail(String email);
	
	@Query ("select review from Review review where review.reviewer = :email")
	Page<Review> getAllProvidedReviews(@Param ("email") String email, Pageable pageable);
	
	@Query ("select review from Review review inner join UserReview user_review on user_review.id.reviewId where user_review.id.userEmail = :email")
	Page<Review> getAllReceivedReviews(@Param (":email") String email, Pageable pageable);
	
	@Transactional
	void deleteByEmail(String email);
}
