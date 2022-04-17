package com.bentbase.backend.gig;

import com.bentbase.backend.gig.education.Education;
import com.bentbase.backend.gig.experience.Experience;
import com.bentbase.backend.tag.Tag;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface GigService {
	
	Page<Gig> getAllGigs(Paginate paginate);
	
	Gig getGigById(Long id);
	
	Page<Gig> searchGigs(String title, List<Tag> tags, Paginate paginate);
	
	Page<Education> getEducations(Long gigId, Paginate paginate);
	
	Page<Experience> getExperiences(Long gigId, Paginate paginate);

//	Page<Project> getProjects(Gig gig, Paginate paginate);
	
	Gig createGig(Gig gig);
	
	Gig updateGig(Map<String, Object> properties);
	
	void deleteGigById(Long id);
}
