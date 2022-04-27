package com.bentbase.backend.application.status;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/applicationStatuses")
public class ApplicationStatusController {
	
	private final ApplicationStatusService applicationStatusService;
	
	public ApplicationStatusController(ApplicationStatusService applicationStatusService) {
		this.applicationStatusService = applicationStatusService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllApplicationStatuses(@RequestParam (defaultValue = "0") int page,
	                                                     @RequestParam (defaultValue = "10") int size,
	                                                     @RequestParam (required = false, defaultValue = "name,asc") String[] sorts) {
		
		Page<ApplicationStatus> usersPage = applicationStatusService.getAllApplicationStatuses(new PageUtil.Paginate(
				page,
				size,
				sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(usersPage);
	}
	
	@GetMapping ("/id/{id}")
	public ApplicationStatus getApplicationStatusById(@PathVariable ("id") Long id) {
		return applicationStatusService.getApplicationStatusById(id);
	}
	
	@GetMapping ("/name/{name}")
	public ApplicationStatus getApplicationStatusByName(@PathVariable ("name") String name) {
		return applicationStatusService.getApplicationStatusByName(name);
	}
	
	@PostMapping ("/create")
	public ApplicationStatus createApplicationStatus(@RequestBody ApplicationStatus applicationStatus) {
		return applicationStatusService.createApplicationStatus(applicationStatus);
	}
	
	@PatchMapping ("/update")
	public ApplicationStatus updateApplicationStatus(@RequestBody Map<String, Object> properties) {
		return applicationStatusService.updateApplicationStatus(properties);
	}
	
	@DeleteMapping ("/id/{id}")
	public String deleteApplicationStatusById(@PathVariable ("id") Long id) {
		applicationStatusService.deleteApplicationStatusById(id);
		return String.format("applicationStatus with id: `%s` is deleted", id);
	}
	
	@DeleteMapping ("/name/{name}")
	public String deleteApplicationStatusByName(@PathVariable ("name") String name) {
		applicationStatusService.deleteApplicationStatusByName(name);
		return String.format("applicationStatus with name: `%s` is deleted", name);
	}
	
}
