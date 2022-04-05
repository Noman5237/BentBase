package com.bentbase.backend.gig;

import com.bentbase.backend.tag.Tag;
import com.bentbase.backend.utils.PageUtil;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GigService {
	
	Page<Gig> getAllGigs(Paginate paginate);
	
	Gig getGigById(String id);
	
	Page<Gig> getGigsByTag(List<Tag> tags, Paginate paginate);
	
	Gig updateGig(Gig gig);
	
	void deleteGigById(String id);
}
