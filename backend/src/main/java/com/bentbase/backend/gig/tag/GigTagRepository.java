package com.bentbase.backend.gig.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GigTagRepository extends JpaRepository<GigTag, GigTagId>, JpaSpecificationExecutor<GigTag> {
	
	@Override
	void deleteById(GigTagId gigTagId);
}