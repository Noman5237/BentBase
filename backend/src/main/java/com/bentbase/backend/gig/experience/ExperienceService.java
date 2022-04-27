package com.bentbase.backend.gig.experience;


import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ExperienceService {
	
	Page<Experience> getAllExperiences(Paginate paginate);
	
	Experience getExperienceById(Long id);
	
	Experience createExperience(Experience experience);
	
	Experience updateExperience(Map<String, Object> properties);
	
	void deleteExperienceById(Long id);
}
