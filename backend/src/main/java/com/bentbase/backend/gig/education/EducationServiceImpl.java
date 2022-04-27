package com.bentbase.backend.gig.education;

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
public class EducationServiceImpl implements EducationService {
	
	private static final Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);
	
	private final EducationRepository educationRepository;
	
	public EducationServiceImpl(EducationRepository educationRepository) {
		this.educationRepository = educationRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Education> getAllEducations(Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), Education.class));
			return educationRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Education.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Education getEducationById(Long id) {
		Optional<Education> education = educationRepository.findById(id);
		if (education.isEmpty()) {
			throw new GetException(Education.class).withError("id", "does not exist");
		}
		
		return education.get();
	}
	
	@SneakyThrows
	@Override
	public Education createEducation(Education education) {
		try {
			return educationRepository.save(education);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Education.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Education updateEducation(Map<String, Object> properties) {
		if (!properties.containsKey("id")) {
			throw new GetException(Education.class).withError("id", "must not be blank");
		}
		
		var id = Long.valueOf((Integer) properties.get("id"));
		var education = this.getEducationById(id);
		
		try {
			PatchUtil.update(education, properties);
		} catch (RESTException exception) {
			throw new UpdateException(Education.class, exception);
		}
		
		return educationRepository.save(education);
	}
	
	@Override
	public void deleteEducationById(Long id) {
		this.getEducationById(id);
		educationRepository.deleteById(id);
	}
}
