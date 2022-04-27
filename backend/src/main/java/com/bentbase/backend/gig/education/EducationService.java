package com.bentbase.backend.gig.education;


import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface EducationService {
	
	Page<Education> getAllEducations(Paginate paginate);
	
	Education getEducationById(Long id);
	
	Education createEducation(Education education);
	
	Education updateEducation(Map<String, Object> properties);
	
	void deleteEducationById(Long id);
}
