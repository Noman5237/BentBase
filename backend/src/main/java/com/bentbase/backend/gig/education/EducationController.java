package com.bentbase.backend.gig.education;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/educations")
public class EducationController {
	
	private final EducationService educationService;
	
	public EducationController(EducationService educationService) {
		this.educationService = educationService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllEducations(
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "10") int size,
			@RequestParam (required = false, defaultValue = "id,asc") String[] sorts) {
		
		Page<Education> educationsPage = educationService.getAllEducations(new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(educationsPage);
	}
	
	@GetMapping ("/{id}")
	public Education getEducationById(@PathVariable ("id") Long id) {
		return educationService.getEducationById(id);
	}
	
	@PostMapping ("/create")
	public Education createEducation(@RequestBody Education education) {
		return educationService.createEducation(education);
	}
	
	@PatchMapping ("/update")
	public Education updateEducation(@RequestBody Map<String, Object> properties) {
		return educationService.updateEducation(properties);
	}
	
	@DeleteMapping ("/{id}")
	public String deleteEducationById(@PathVariable ("id") Long id) {
		educationService.deleteEducationById(id);
		return String.format("education with id: `%s` is deleted", id);
	}
}
