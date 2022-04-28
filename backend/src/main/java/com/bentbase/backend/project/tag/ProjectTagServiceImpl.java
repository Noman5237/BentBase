package com.bentbase.backend.project.tag;

import com.bentbase.backend.project.Project;
import com.bentbase.backend.utils.PageUtil;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTagServiceImpl implements ProjectTagService {
	
	private final ProjectTagRepository projectTagRepository;
	
	public ProjectTagServiceImpl(ProjectTagRepository projectTagRepository) {
		this.projectTagRepository = projectTagRepository;
	}
	
	@Override
	public ProjectTag createProjectTag(ProjectTag projectTag) {
		return projectTagRepository.save(projectTag);
	}
	
	@Override
	public void deleteProjectTagById(ProjectTagId projectTagId) {
		projectTagRepository.deleteById(projectTagId);
	}
}
