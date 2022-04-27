package com.bentbase.backend.project;

import com.bentbase.backend.application.Application;
import com.bentbase.backend.order.Order;
import com.bentbase.backend.tag.Tag;
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
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
	
	@Override
	Page<Project> findAll(Pageable pageable);
	
	@Override
	Optional<Project> findById(Long id);
	
	@Query ("select a from Application a where a.projectId = :id")
	Page<Application> getAllApplications(@Param ("id") Long id, Pageable pageable);
	
	@Query ("select tag from Tag tag inner join ProjectTag project_tag on tag.id = project_tag.id.tagId and project_tag.id.projectId = :project_id")
	Page<Tag> getAllTags(@Param ("project_id") Long projectTag, Pageable pageable);
	
	@Query ("select o from Order o where o.projectId = :project_id")
	Page<Order> getAllOrders(@Param ("project_id") Long projectId, Pageable pageable);
	
	@Query (nativeQuery = true, value = "select * from project where project.id in (select distinct project.id from project join project_tag on project.id = project_tag.project_id where project_tag.tag_id in (select tag.id from tag where tag.name in :included_tags)) order by utl_match.edit_distance_similarity(project.title, :title) desc")
	Page<Project> filterProjects(@Param ("title") String title,
	                             @Param ("included_tags") String[] includedTags,
	                             Pageable pageable);
	
	@Transactional
	void deleteById(Long id);
}