package com.bentbase.backend.project;

import com.bentbase.backend.tag.Tag;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectInterface {
	
	Page<Project> getAllProject(Paginate paginate);
	
	Project getProjectById(String id);
	
	Page<Project> getProjectsByTag(List<Tag> tags, Paginate paginate);
	
	Project createProject(Project project);
	
	Project updateProject(Project project);
	
	void deleteProjectById(String id);
}
