package com.bentbase.backend.project.tag;

import com.bentbase.backend.project.Project;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectTagService {
	
	ProjectTag createProjectTag(ProjectTag projectTag);
	
	void deleteProjectTagById(ProjectTagId projectTagId);
	
}
