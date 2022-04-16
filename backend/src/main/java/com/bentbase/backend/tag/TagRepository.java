package com.bentbase.backend.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
	
	@Override
	Page<Tag> findAll(Pageable pageable);
	
	Tag findByNameIs(String name);
}
