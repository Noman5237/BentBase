package com.bentbase.backend.gig.education;


import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

public interface EducationService {
	
	Page<Education> getAllEducations(Paginate paginate);
	
	Education getEducationById(String id);
	
	Education updateEducation(Education education);
	
	void deleteEducationById(String id);
}
