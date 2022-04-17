package com.bentbase.backend.gig.experience;

import com.bentbase.backend.seller.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, String>, JpaSpecificationExecutor<Seller> {
	
	@Override
	Page<Experience> findAll(Pageable pageable);
	
	Optional<Experience> findById(Long id);
	
	@Transactional
	void deleteById(Long id);
}