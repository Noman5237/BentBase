package com.bentbase.backend.gig.tag;

import com.bentbase.backend.gig.Gig;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GigTagRepository extends JpaRepository<GigTag, GigTagId>, JpaSpecificationExecutor<GigTag> {
	
	@Override
	void deleteById(GigTagId gigTagId);
}