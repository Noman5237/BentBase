package com.bentbase.backend.tag;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TagService {
	
	Page<Tag> getAllTags(Paginate paginate);
	
	Tag getTagById(Long id);
	
	Tag getTagByName(String name);
	
	Page<Tag> searchTags(String query, Paginate paginate);
	
	Tag createTag(Tag tag);
	
	Tag updateTag(Map<String, Object> properties);
	
	void deleteTagById(Long id);
	
	void deleteTagByName(String name);
}
