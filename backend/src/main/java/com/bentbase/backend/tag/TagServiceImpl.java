package com.bentbase.backend.tag;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
	
	@Override
	public Page<Tag> getAllTags(PageUtil.Paginate paginate) {
		return null;
	}
	
	@Override
	public Tag getTagByName(String name) {
		return null;
	}
	
	@Override
	public Page<Tag> searchTags(String query, PageUtil.Paginate paginate) {
		return null;
	}
	
	@Override
	public Tag createTag(Tag tag) {
		return null;
	}
	
	@Override
	public Tag updateTag(Tag tag) {
		return null;
	}
	
	@Override
	public void deleteTagByName(String name) {
	
	}
}
