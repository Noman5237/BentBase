package com.bentbase.backend.gig.experience;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/experiences")
public class ExperienceController {
	
	private final ExperienceService experienceService;
	
	public ExperienceController(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllExperiences(
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "10") int size,
			@RequestParam (required = false, defaultValue = "id,asc") String[] sorts) {
		
		Page<Experience> experiencesPage = experienceService.getAllExperiences(new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(experiencesPage);
	}
	
	@GetMapping ("/{id}")
	public Experience getExperienceById(@PathVariable ("id") Long id) {
		return experienceService.getExperienceById(id);
	}
	
	@PostMapping ("/create")
	public Experience createExperience(@RequestBody Experience experience) {
		return experienceService.createExperience(experience);
	}
	
	@PatchMapping ("/update")
	public Experience updateExperience(@RequestBody Map<String, Object> properties) {
		return experienceService.updateExperience(properties);
	}
	
	@DeleteMapping ("/{id}")
	public String deleteExperienceById(@PathVariable ("id") Long id) {
		experienceService.deleteExperienceById(id);
		return String.format("experience with id: `%s` is deleted", id);
	}
}
