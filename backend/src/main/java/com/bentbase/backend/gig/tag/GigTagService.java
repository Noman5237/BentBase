package com.bentbase.backend.gig.tag;

import com.bentbase.backend.gig.Gig;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GigTagService {
	
	GigTag createGigTag(GigTag gigTag);
	
	void deleteGigTagById(GigTagId gigTagId);
	
}
