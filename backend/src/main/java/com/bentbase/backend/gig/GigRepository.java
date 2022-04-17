package com.bentbase.backend.gig;

import com.bentbase.backend.gig.education.Education;
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
public interface GigRepository extends JpaRepository<Gig, Long>, JpaSpecificationExecutor<Gig> {
	
	@Override
	Page<Gig> findAll(Pageable pageable);
	
	@Override
	Optional<Gig> findById(Long id);
	
	@Query ("select e from Education e where e.gigId = :id")
	Page<Education> getAllEducations(@Param ("id") Long id, Pageable pageable);
	
	@Transactional
	void deleteById(Long id);
}