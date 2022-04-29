package com.bentbase.backend.review;

import com.bentbase.backend.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
	
	@Override
	Page<Review> findAll(Pageable pageable);
	
	@Override
	Optional<Review> findById(Long id);
	
	@Transactional
	void deleteById(Long id);
}
