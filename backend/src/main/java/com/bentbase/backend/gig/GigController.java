package com.bentbase.backend.gig;

import com.bentbase.backend.tag.Tag;
import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/gigs")
public class GigController {
	
	private final GigService gigService;
	
	public GigController(GigService gigService) {
		this.gigService = gigService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllGigs(
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "10") int size,
			@RequestParam (required = false, defaultValue = "id,asc") String[] sorts) {
		
		Page<Gig> gigsPage = gigService.getAllGigs(new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(gigsPage);
	}
	
	@GetMapping ("/{id}")
	public Gig getGigById(@PathVariable ("id") Long id) {
		return gigService.getGigById(id);
	}
	
	@GetMapping ("/{id}/educations")
	public Map<String, Object> getAllEducations(@PathVariable ("id") Long id,
	                                            @RequestParam (defaultValue = "0") int page,
	                                            @RequestParam (defaultValue = "10") int size,
	                                            @RequestParam (required = false, defaultValue = "id,asc") String[] sorts) {
		
		var educationsPage = gigService.getEducations(id, new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(educationsPage);
	}
	
	@GetMapping ("/{id}/experiences")
	public Map<String, Object> getAllExperiences(@PathVariable ("id") Long id,
	                                             @RequestParam (defaultValue = "0") int page,
	                                             @RequestParam (defaultValue = "10") int size,
	                                             @RequestParam (required = false, defaultValue = "id,asc") String[] sorts) {
		
		var experiencesPage = gigService.getExperiences(id, new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(experiencesPage);
	}
	
	@GetMapping ("/{id}/tags")
	public List<Tag> getAllTags(@PathVariable ("id") Long id,
	                            @RequestParam (defaultValue = "0") int page,
	                            @RequestParam (defaultValue = "10") int size,
	                            @RequestParam (required = false, defaultValue = "name,asc") String[] sorts) {
		
		var tagsPage = gigService.getTags(id, new PageUtil.Paginate(page, size, sorts));

//		return PageUtil.createResponseWithPaginatedMeta(tagsPage);
		return tagsPage;
	}
	
	@PostMapping ("/create")
	public Gig createGig(@RequestBody Gig gig) {
		return gigService.createGig(gig);
	}
	
	@PatchMapping ("/update")
	public Gig updateGig(@RequestBody Map<String, Object> properties) {
		return gigService.updateGig(properties);
	}
	
	@DeleteMapping ("/{id}")
	public String deleteGigById(@PathVariable ("id") Long id) {
		gigService.deleteGigById(id);
		return String.format("gig with id: `%s` is deleted", id);
	}
}
