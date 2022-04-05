package com.bentbase.backend.tag;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

public interface TagService {
	
	Page<Tag> getAllTags(Paginate paginate);
	
	Tag getTagByName(String name);
	
	Page<Tag> searchTags(String query, int page, int size, String[] sorts);
	
	Tag createTag(Tag tag);
	
	Tag updateTag(Tag tag);
	
	void deleteTagByName(String name);
}
