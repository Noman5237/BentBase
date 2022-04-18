package com.bentbase.backend.project;

import com.bentbase.backend.application.Application;
import com.bentbase.backend.tag.Tag;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProjectService {
	
	Page<Project> getAllProjects(Paginate paginate);
	
	Project getProjectById(Long id);
	
	Page<Project> searchProjects(String title, List<Tag> tags, Paginate paginate);
	
	Page<Application> getApplications(Long id, Paginate paginate);
	
	Project createProject(Project project);
	
	Project updateProject(Map<String, Object> properties);
	
	void deleteProjectById(Long id);
}
