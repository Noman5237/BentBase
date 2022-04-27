package com.bentbase.backend.gig;

import com.bentbase.backend.gig.education.Education;
import com.bentbase.backend.gig.experience.Experience;
import com.bentbase.backend.order.Order;
import com.bentbase.backend.tag.Tag;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.Map;

public interface GigService {
	
	Page<Gig> getAllGigs(Paginate paginate);
	
	Gig getGigById(Long id);
	
	Page<Education> getEducations(Long gigId, Paginate paginate);
	
	Page<Experience> getExperiences(Long gigId, Paginate paginate);
	
	Page<Tag> getTags(Long gigId, Paginate paginate);
	
	Page<Order> getOrders(Long gigId, Paginate paginate);
	
	Long getEarning(Long gigId, Date startDate, Date endDate);
	
	Page<Gig> filterGigs(String title, String[] includedTags, Paginate paginate);
	
	Gig createGig(Gig gig);
	
	Gig updateGig(Map<String, Object> properties);
	
	void deleteGigById(Long id);
}
