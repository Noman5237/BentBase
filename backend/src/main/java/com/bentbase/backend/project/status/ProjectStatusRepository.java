package com.bentbase.backend.project.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long>, JpaSpecificationExecutor<ProjectStatus> {
	
	@Override
	Page<ProjectStatus> findAll(Pageable pageable);
	
	@Override
	Optional<ProjectStatus> findById(Long id);
	
	Optional<ProjectStatus> findByName(String name);
	
	@Transactional
	void deleteByName(String name);
}
