package com.bentbase.backend.project.application;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

public interface ApplicationService {
	
	Page<Application> getAllApplications(Paginate paginate);
	
	Page<Application> getAllApplications(Application.Status status, Paginate paginate);
	
	Application getApplicationById(String id);
	
	String getCoverLetter(Application application, boolean preview);
	
	Application createApplication(Application application);
	
	Application updateApplication(Application application);
	
	void deleteApplicationById(String id);
}
