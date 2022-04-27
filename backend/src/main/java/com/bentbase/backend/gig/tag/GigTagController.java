package com.bentbase.backend.gig.tag;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/gigTags")
public class GigTagController {
	
	private final GigTagService gigTagService;
	
	public GigTagController(GigTagService gigTagService) {
		this.gigTagService = gigTagService;
	}
	
	@PostMapping ("/create")
	public GigTag createGigTag(@RequestBody GigTag gigTag) {
		return gigTagService.createGigTag(gigTag);
	}
	
	@DeleteMapping ()
	public String deleteGigTagById(@RequestBody GigTagId id) {
		gigTagService.deleteGigTagById(id);
		return String.format("gigTag with id: `%s` is deleted", id);
	}
}
