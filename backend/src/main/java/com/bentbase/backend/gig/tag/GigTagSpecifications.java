package com.bentbase.backend.gig.tag;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface GigTagSpecifications {
	
	default Specification<GigTag> hasTagNames(List<String> tagNames) {
		return null;
	}
}
