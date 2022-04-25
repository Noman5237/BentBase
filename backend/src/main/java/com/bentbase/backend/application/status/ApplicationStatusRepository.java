package com.bentbase.backend.application.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Long>, JpaSpecificationExecutor<ApplicationStatus> {
	
	@Override
	Page<ApplicationStatus> findAll(Pageable pageable);
	
	@Override
	Optional<ApplicationStatus> findById(Long id);
	
	Optional<ApplicationStatus> findByName(String name);
	
	@Transactional
	void deleteByName(String name);
}
