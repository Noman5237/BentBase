package com.bentbase.backend.project;

import com.bentbase.backend.application.Application;
import com.bentbase.backend.tag.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
	
	@Override
	Page<Project> findAll(Pageable pageable);
	
	@Override
	Optional<Project> findById(Long id);
	
	@Query ("select a from Application a where a.projectId = :id")
	Page<Application> getAllApplications(@Param ("id") Long id, Pageable pageable);
	
	@Query ("select tag from Tag tag inner join ProjectTag project_tag on tag.id = project_tag.id.tagId and project_tag.id.projectId = :project_id")
	Page<Tag> getAllTags(@Param ("project_id") Long projectTag, Pageable pageable);
	
	@Transactional
	void deleteById(Long id);
}