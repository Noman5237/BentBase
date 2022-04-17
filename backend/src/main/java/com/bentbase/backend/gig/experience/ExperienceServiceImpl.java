package com.bentbase.backend.gig.experience;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.utils.PatchUtil;
import com.bentbase.backend.utils.SortUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.Map;
import java.util.Optional;

import static com.bentbase.backend.utils.PageUtil.Paginate;

@Service
public class ExperienceServiceImpl implements ExperienceService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExperienceServiceImpl.class);
	
	private final ExperienceRepository experienceRepository;
	
	public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
		this.experienceRepository = experienceRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Experience> getAllExperiences(Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), Experience.class));
			return experienceRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Experience.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Experience getExperienceById(Long id) {
		Optional<Experience> experience = experienceRepository.findById(id);
		if (experience.isEmpty()) {
			throw new GetException(Experience.class).withError("id", "does not exist");
		}
		
		return experience.get();
	}
	
	@SneakyThrows
	@Override
	public Experience createExperience(Experience experience) {
		try {
			return experienceRepository.save(experience);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Experience.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Experience updateExperience(Map<String, Object> properties) {
		if (!properties.containsKey("id")) {
			throw new GetException(Experience.class).withError("id", "must not be blank");
		}
		
		var id = Long.valueOf((Integer) properties.get("id"));
		var experience = this.getExperienceById(id);
		
		try {
			PatchUtil.update(experience, properties);
		} catch (RESTException exception) {
			throw new UpdateException(Experience.class, exception);
		}
		
		return experienceRepository.save(experience);
	}
	
	@Override
	public void deleteExperienceById(Long id) {
		this.getExperienceById(id);
		experienceRepository.deleteById(id);
	}
}
