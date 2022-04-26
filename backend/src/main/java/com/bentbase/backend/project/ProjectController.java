package com.bentbase.backend.project;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/projects")
public class ProjectController {
	
	private final ProjectService projectService;
	
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllProjects(@RequestParam (defaultValue = "0") int page,
	                                          @RequestParam (defaultValue = "10") int size,
	                                          @RequestParam (required = false, defaultValue = "id,asc") String[] sorts) {
		
		Page<Project> projectsPage = projectService.getAllProjects(new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(projectsPage);
	}
	
	@GetMapping ("/{id}")
	public Project getProjectById(@PathVariable ("id") Long id) {
		return projectService.getProjectById(id);
	}
	
	@GetMapping ("/{id}/applications")
	public Map<String, Object> getAllApplications(@PathVariable ("id") Long id,
	                                              @RequestParam (defaultValue = "0") int page,
	                                              @RequestParam (defaultValue = "10") int size,
	                                              @RequestParam (required = false, defaultValue = "id,asc") String[] sorts) {
		
		var educationsPage = projectService.getApplications(id, new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(educationsPage);
	}
	
	@GetMapping ("/{id}/tags")
	public Map<String, Object> getAllTags(@PathVariable ("id") Long id,
	                                      @RequestParam (defaultValue = "0") int page,
	                                      @RequestParam (defaultValue = "10") int size,
	                                      @RequestParam (required = false, defaultValue = "name,asc") String[] sorts) {
		
		var tagsPage = projectService.getTags(id, new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(tagsPage);
	}
	
	@PostMapping ("/create")
	public Project createProject(@RequestBody Project project) {
		return projectService.createProject(project);
	}
	
	@PatchMapping ("/update")
	public Project updateProject(@RequestBody Map<String, Object> properties) {
		return projectService.updateProject(properties);
	}
	
	@DeleteMapping ("/{id}")
	public String deleteProjectById(@PathVariable ("id") Long id) {
		projectService.deleteProjectById(id);
		return String.format("project with id: `%s` is deleted", id);
	}
}
