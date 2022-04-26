package com.bentbase.backend.project.status;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/projectStatuses")
public class ProjectStatusController {
	
	private final ProjectStatusService projectStatusService;
	
	public ProjectStatusController(ProjectStatusService projectStatusService) {
		this.projectStatusService = projectStatusService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllProjectStatuses(@RequestParam (defaultValue = "0") int page,
	                                                 @RequestParam (defaultValue = "10") int size,
	                                                 @RequestParam (required = false, defaultValue = "name,asc") String[] sorts) {
		
		Page<ProjectStatus> usersPage = projectStatusService.getAllProjectStatuses(new PageUtil.Paginate(
				page,
				size,
				sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(usersPage);
	}
	
	@GetMapping ("/id/{id}")
	public ProjectStatus getProjectStatusById(@PathVariable ("id") Long id) {
		return projectStatusService.getProjectStatusById(id);
	}
	
	@GetMapping ("/name/{name}")
	public ProjectStatus getProjectStatusByName(@PathVariable ("name") String name) {
		return projectStatusService.getProjectStatusByName(name);
	}
	
	@PostMapping ("/create")
	public ProjectStatus createProjectStatus(@RequestBody ProjectStatus projectStatus) {
		return projectStatusService.createProjectStatus(projectStatus);
	}
	
	@PatchMapping ("/update")
	public ProjectStatus updateProjectStatus(@RequestBody Map<String, Object> properties) {
		return projectStatusService.updateProjectStatus(properties);
	}
	
	@DeleteMapping ("/id/{id}")
	public String deleteProjectStatusById(@PathVariable ("id") Long id) {
		projectStatusService.deleteProjectStatusById(id);
		return String.format("projectStatus with id: `%s` is deleted", id);
	}
	
	@DeleteMapping ("/name/{name}")
	public String deleteProjectStatusByName(@PathVariable ("name") String name) {
		projectStatusService.deleteProjectStatusByName(name);
		return String.format("projectStatus with name: `%s` is deleted", name);
	}
	
}
