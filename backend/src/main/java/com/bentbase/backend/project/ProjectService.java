package com.bentbase.backend.project;

import com.bentbase.backend.project.application.Application;
import com.bentbase.backend.tag.Tag;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
	
	Page<Project> getAllProject(Paginate paginate);
	
	Project getProjectById(String id);
	
	Page<Project> searchProjects(String title, List<Tag> tags, Paginate paginate);
	
	Page<Application> getApplications(Project project, Paginate paginate);
	
	Page<Application> getApplications(Project project, Application.Status status, Paginate paginate);
	
	Project createProject(Project project);
	
	Project updateProject(Project project);
	
	void deleteProjectById(String id);
}
