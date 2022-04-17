package com.bentbase.backend.tag;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/tags")
public class TagController {
	
	private final TagService tagService;
	
	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllTags(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size, @RequestParam (required = false, defaultValue = "name,asc") String[] sorts) {
		
		Page<Tag> usersPage = tagService.getAllTags(new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(usersPage);
	}
	
	@GetMapping ("/id/{id}")
	public Tag getTagById(@PathVariable ("id") Long id) {
		return tagService.getTagById(id);
	}
	
	@GetMapping ("/name/{name}")
	public Tag getTagByName(@PathVariable ("name") String name) {
		return tagService.getTagByName(name);
	}
	
	@PostMapping ("/create")
	public Tag createTag(@RequestBody Tag tag) {
		return tagService.createTag(tag);
	}
	
	@PatchMapping ("/update")
	public Tag updateTag(@RequestBody Map<String, Object> properties) {
		return tagService.updateTag(properties);
	}
	
	@DeleteMapping ("/id/{id}")
	public String deleteTagById(@PathVariable ("id") Long id) {
		tagService.deleteTagById(id);
		return String.format("tag with id: `%s` is deleted", id);
	}
	
	@DeleteMapping ("/name/{name}")
	public String deleteTagByName(@PathVariable ("name") String name) {
		tagService.deleteTagByName(name);
		return String.format("tag with name: `%s` is deleted", name);
	}
	
	
}
