package com.bentbase.backend.gig.tag;

import com.bentbase.backend.gig.Gig;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GigTagService {
	
	Page<Gig> getGigsByTags(List<String> includingTagNames, List<String> excludingTagNames, Paginate paginate);
	
	GigTag createGigTag(GigTag gigTag);
	
	void deleteGigTagById(GigTagId gigTagId);
	
}
