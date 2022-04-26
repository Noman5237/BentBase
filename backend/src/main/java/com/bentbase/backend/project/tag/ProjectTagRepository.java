package com.bentbase.backend.project.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTagRepository extends JpaRepository<ProjectTag, ProjectTagId>, JpaSpecificationExecutor<ProjectTag> {
	
	@Override
	void deleteById(ProjectTagId projectTagId);
}