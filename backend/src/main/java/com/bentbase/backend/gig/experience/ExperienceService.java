package com.bentbase.backend.gig.experience;


import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

public interface ExperienceService {
	
	Page<Experience> getAllExperiences(Paginate paginate);
	
	Experience getExperienceById(String id);
	
	Experience updateExperience(Experience experience);
	
	void deleteExperienceById(String id);
}
