package com.bentbase.backend.tag;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface TagService {
	
	Page<Tag> getAllTags(Paginate paginate);
	
	Tag getTagByName(String name);
	
	Page<Tag> searchTags(String query, Paginate paginate);
	
	Tag createTag(Tag tag);
	
	Tag updateTag(Tag tag);
	
	void deleteTagByName(String name);
}
