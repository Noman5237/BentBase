package com.bentbase.backend.application.status;

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
public class ApplicationStatusServiceImpl implements ApplicationStatusService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final ApplicationStatusRepository applicationStatusRepository;
	
	public ApplicationStatusServiceImpl(ApplicationStatusRepository applicationStatusRepository) {
		this.applicationStatusRepository = applicationStatusRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<ApplicationStatus> getAllApplicationStatuses(PageUtil.Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(),
			                                        paginate.getSize(),
			                                        SortUtil.getOrdersFromStringArray(paginate.getSorts(),
			                                                                          ApplicationStatus.class));
			return applicationStatusRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(ApplicationStatus.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public ApplicationStatus getApplicationStatusById(Long id) {
		Optional<ApplicationStatus> applicationStatus = applicationStatusRepository.findById(id);
		if (applicationStatus.isEmpty()) {
			throw new GetException(ApplicationStatus.class).withError("id", "does not exist");
		}
		
		return applicationStatus.get();
	}
	
	@SneakyThrows
	@Override
	public ApplicationStatus getApplicationStatusByName(String name) {
		Optional<ApplicationStatus> applicationStatus = applicationStatusRepository.findByName(name);
		if (applicationStatus.isEmpty()) {
			throw new GetException(ApplicationStatus.class).withError("name", "does not exist");
		}
		
		return applicationStatus.get();
	}
	
	@SneakyThrows
	@Override
	public ApplicationStatus createApplicationStatus(ApplicationStatus applicationStatus) {
		Optional<ApplicationStatus> existingApplicationStatus = applicationStatusRepository.findByName(applicationStatus.getName());
		if (existingApplicationStatus.isPresent()) {
			throw new CreateException(ApplicationStatus.class).withError("name", "already exists");
		}
		
		try {
			return applicationStatusRepository.save(applicationStatus);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(ApplicationStatus.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public ApplicationStatus updateApplicationStatus(Map<String, Object> properties) {
		if (!properties.containsKey("id") && !properties.containsKey("name")) {
			throw new GetException(ApplicationStatus.class).withError("id", "must not be blank")
			                                               .withError("name", "must not be blank");
		}
		
		var applicationStatus = properties.containsKey("id") ? getApplicationStatusById(Long.valueOf((Integer) properties.get(
				"id"))) : getApplicationStatusByName((String) properties.get("name"));
		
		try {
			PatchUtil.update(applicationStatus, properties);
		} catch (RESTException exception) {
			throw new UpdateException(User.class, exception);
		}
		
		return applicationStatusRepository.save(applicationStatus);
	}
	
	@Override
	public void deleteApplicationStatusById(Long id) {
		this.getApplicationStatusById(id);
		applicationStatusRepository.deleteById(id);
	}
	
	@Override
	public void deleteApplicationStatusByName(String name) {
		this.getApplicationStatusByName(name);
		applicationStatusRepository.deleteByName(name);
	}
}
