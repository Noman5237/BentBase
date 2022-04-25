package com.bentbase.backend.tag;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.user.User;
import com.bentbase.backend.user.UserServiceImpl;
import com.bentbase.backend.utils.PageUtil;
import com.bentbase.backend.utils.PatchUtil;
import com.bentbase.backend.utils.SortUtil;
import lombok.SneakyThrows;
import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.Map;
import java.util.Optional;

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
	
	@SneakyThrows
	@Override
	public Tag getTagById(Long id) {
		Optional<Tag> tag = tagRepository.findById(id);
		if (tag.isEmpty()) {
			throw new GetException(Tag.class).withError("id", "does not exist");
		}
		
		return tag.get();
	}
	
	@SneakyThrows
	@Override
	public Tag getTagByName(String name) {
		Optional<Tag> tag = tagRepository.findByName(name);
		if (tag.isEmpty()) {
			throw new GetException(Tag.class).withError("name", "does not exist");
		}
		
		return tag.get();
	}
	
	@Override
	public Page<Tag> searchTags(String query, PageUtil.Paginate paginate) {
		throw new NotYetImplementedException();
	}
	
	@SneakyThrows
	@Override
	public Tag createTag(Tag tag) {
		Optional<Tag> existingTag = tagRepository.findByName(tag.getName());
		if (existingTag.isPresent()) {
			throw new CreateException(Tag.class).withError("name", "already exists");
		}
		
		try {
			return tagRepository.save(tag);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Tag.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Tag updateTag(Map<String, Object> properties) {
		if (!properties.containsKey("id") && !properties.containsKey("name")) {
			throw new GetException(Tag.class)
					.withError("id", "must not be blank")
					.withError("name", "must not be blank");
		}
		
		var tag = properties.containsKey("id") ? getTagById(Long.valueOf((Integer) properties.get("id"))) : getTagByName((String) properties.get("name"));
		
		try {
			PatchUtil.update(tag, properties);
		} catch (RESTException exception) {
			throw new UpdateException(User.class, exception);
		}
		
		return tagRepository.save(tag);
	}
	
	@Override
	public void deleteTagById(Long id) {
		this.getTagById(id);
		tagRepository.deleteById(id);
	}
	
	@Override
	public void deleteTagByName(String name) {
		this.getTagByName(name);
		tagRepository.deleteByName(name);
	}
}
