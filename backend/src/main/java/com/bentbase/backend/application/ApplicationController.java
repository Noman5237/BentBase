package com.bentbase.backend.application;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/applications")
public class ApplicationController {
	
	private final ApplicationService applicationService;
	
	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllApplications(
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "10") int size,
			@RequestParam (required = false, defaultValue = "id,asc") String[] sorts) {
		
		Page<Application> applicationsPage = applicationService.getAllApplications(new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(applicationsPage);
	}
	
	@GetMapping ("/{id}")
	public Application getApplicationById(@PathVariable ("id") Long id) {
		return applicationService.getApplicationById(id);
	}
	
	@PostMapping ("/create")
	public Application createApplication(@RequestBody Application application) {
		return applicationService.createApplication(application);
	}
	
	@PatchMapping ("/update")
	public Application updateApplication(@RequestBody Map<String, Object> properties) {
		return applicationService.updateApplication(properties);
	}
	
	@DeleteMapping ("/{id}")
	public String deleteApplicationById(@PathVariable ("id") Long id) {
		applicationService.deleteApplicationById(id);
		return String.format("application with id: `%s` is deleted", id);
	}
}
