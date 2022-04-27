package com.bentbase.backend.project;

import com.bentbase.backend.application.Application;
import com.bentbase.backend.order.Order;
import com.bentbase.backend.tag.Tag;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ProjectService {
	
	Page<Project> getAllProjects(Paginate paginate);
	
	Project getProjectById(Long id);
	
	Page<Application> getApplications(Long id, Paginate paginate);
	
	Page<Tag> getTags(Long projectId, Paginate paginate);
	
	Page<Order> getOrders(Long projectId, Paginate paginate);
	
	Page<Project> filterProjects(String title, String[] includedTags, Paginate paginate);
	
	Project createProject(Project project);
	
	Project updateProject(Map<String, Object> properties);
	
	void deleteProjectById(Long id);
}
