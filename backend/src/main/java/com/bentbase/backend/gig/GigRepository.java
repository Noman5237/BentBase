package com.bentbase.backend.gig;

import com.bentbase.backend.gig.education.Education;
import com.bentbase.backend.gig.experience.Experience;
import com.bentbase.backend.tag.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GigRepository extends JpaRepository<Gig, Long>, JpaSpecificationExecutor<Gig> {
	
	@Override
	Page<Gig> findAll(Pageable pageable);
	
	@Override
	Optional<Gig> findById(Long id);
	
	@Query ("select education from Education education where education.gigId = :id")
	Page<Education> getAllEducations(@Param ("id") Long id, Pageable pageable);
	
	@Query ("select experience from Experience experience where experience.gigId = :id")
	Page<Experience> getAllExperiences(@Param ("id") Long gigId, Pageable pageable);
	
	@Query ("select tag from Tag tag inner join GigTag gig_tag on tag.id= gig_tag.id.tagId and gig_tag.id.gigId= :gig_id")
	List<Tag> getAllTags(@Param ("gig_id") Long gigId);
	
	@Override
	@Transactional
	void deleteById(Long id);
}