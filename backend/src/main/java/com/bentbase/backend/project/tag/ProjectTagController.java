package com.bentbase.backend.project.tag;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/projectTags")
public class ProjectTagController {
	
	private final ProjectTagService projectTagService;
	
	public ProjectTagController(ProjectTagService projectTagService) {
		this.projectTagService = projectTagService;
	}
	
	@PostMapping ("/create")
	public ProjectTag createProjectTag(@RequestBody ProjectTag projectTag) {
		return projectTagService.createProjectTag(projectTag);
	}
	
	@DeleteMapping ()
	public String deleteProjectTagById(@RequestBody ProjectTagId id) {
		projectTagService.deleteProjectTagById(id);
		return String.format("projectTag with id: `%s` is deleted", id);
	}
}
