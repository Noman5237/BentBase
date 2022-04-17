package com.bentbase.backend.application;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ApplicationService {
	
	Page<Application> getAllApplications(Paginate paginate);
	
	Application getApplicationById(Long id);
	
	Application createApplication(Application application);
	
	Application updateApplication(Map<String, Object> properties);
	
	void deleteApplicationById(Long id);
}
