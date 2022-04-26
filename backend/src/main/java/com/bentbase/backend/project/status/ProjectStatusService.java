package com.bentbase.backend.project.status;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ProjectStatusService {
	
	Page<ProjectStatus> getAllProjectStatuses(Paginate paginate);
	
	ProjectStatus getProjectStatusById(Long id);
	
	ProjectStatus getProjectStatusByName(String name);
	
	ProjectStatus createProjectStatus(ProjectStatus projectStatus);
	
	ProjectStatus updateProjectStatus(Map<String, Object> properties);
	
	void deleteProjectStatusById(Long id);
	
	void deleteProjectStatusByName(String name);
}
