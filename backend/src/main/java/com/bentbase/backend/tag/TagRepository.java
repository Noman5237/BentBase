package com.bentbase.backend.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	@Override
	Page<Tag> findAll(Pageable pageable);
	
	Optional<Tag> findById(Long id);
	
	Optional<Tag> findByName(String name);
	
	@Transactional
	void deleteByName(String name);
}
