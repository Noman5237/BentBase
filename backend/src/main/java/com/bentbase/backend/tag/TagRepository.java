package com.bentbase.backend.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
	
	@Override
	Page<Tag> findAll(Pageable pageable);
	
	@Override
	Optional<Tag> findById(Long id);
	
	Optional<Tag> findByName(String name);
	
	@Query (nativeQuery = true, value = "select * from Tag order by utl_match.edit_distance_similarity(tag.name, :query) desc")
	Page<Tag> searchTags(@Param ("query") String query, Pageable pageable);
	
	@Transactional
	void deleteByName(String name);
}
