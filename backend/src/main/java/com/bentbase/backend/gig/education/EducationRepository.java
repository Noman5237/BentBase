package com.bentbase.backend.gig.education;

import com.bentbase.backend.seller.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education, String>, JpaSpecificationExecutor<Education> {
	
	@Override
	Page<Education> findAll(Pageable pageable);
	
	Optional<Education> findById(Long id);
	
	@Transactional
	void deleteById(Long id);
}