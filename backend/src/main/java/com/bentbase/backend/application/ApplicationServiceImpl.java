package com.bentbase.backend.application;

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
public class ApplicationServiceImpl implements ApplicationService {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);
	
	private final ApplicationRepository applicationRepository;
	
	public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Application> getAllApplications(Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), Application.class));
			return applicationRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Application.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Application getApplicationById(Long id) {
		Optional<Application> application = applicationRepository.findById(id);
		if (application.isEmpty()) {
			throw new GetException(Application.class).withError("id", "does not exist");
		}
		
		return application.get();
	}
	
	@SneakyThrows
	@Override
	public Application createApplication(Application application) {
		try {
			return applicationRepository.save(application);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Application.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Application updateApplication(Map<String, Object> properties) {
		if (!properties.containsKey("id")) {
			throw new GetException(Application.class).withError("id", "must not be blank");
		}
		
		var id = Long.valueOf((Integer) properties.get("id"));
		var application = this.getApplicationById(id);
		
		try {
			PatchUtil.update(application, properties);
		} catch (RESTException exception) {
			throw new UpdateException(Application.class, exception);
		}
		
		return applicationRepository.save(application);
	}
	
	@Override
	public void deleteApplicationById(Long id) {
		this.getApplicationById(id);
		applicationRepository.deleteById(id);
	}
}
