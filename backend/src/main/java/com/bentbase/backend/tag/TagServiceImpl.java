package com.bentbase.backend.tag;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.user.UserServiceImpl;
import com.bentbase.backend.utils.PageUtil;
import com.bentbase.backend.utils.SortUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final TagRepository tagRepository;
	
	public TagServiceImpl(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Tag> getAllTags(PageUtil.Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), Tag.class));
			return tagRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Tag.class, exception);
		}
	}
	
	@Override
	public Tag getTagById(Long Id) {
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
	public void deleteTagById(String name) {
	
	}
	
	@Override
	public void deleteTagByName(String name) {
	
	}
}
