package com.bentbase.backend.application.status;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ApplicationStatusService {
	
	Page<ApplicationStatus> getAllApplicationStatuses(Paginate paginate);
	
	ApplicationStatus getApplicationStatusById(Long id);
	
	ApplicationStatus getApplicationStatusByName(String name);
	
	ApplicationStatus createApplicationStatus(ApplicationStatus applicationStatus);
	
	ApplicationStatus updateApplicationStatus(Map<String, Object> properties);
	
	void deleteApplicationStatusById(Long id);
	
	void deleteApplicationStatusByName(String name);
}
