package com.bentbase.backend.gig.tag;

import com.bentbase.backend.gig.Gig;
import com.bentbase.backend.utils.PageUtil;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GigTagServiceImpl implements GigTagService {
	
	private final GigTagRepository gigTagRepository;
	
	public GigTagServiceImpl(GigTagRepository gigTagRepository) {
		this.gigTagRepository = gigTagRepository;
	}
	
	@Override
	public GigTag createGigTag(GigTag gigTag) {
		return gigTagRepository.save(gigTag);
	}
	
	@Override
	public void deleteGigTagById(GigTagId gigTagId) {
		gigTagRepository.deleteById(gigTagId);
	}
}
