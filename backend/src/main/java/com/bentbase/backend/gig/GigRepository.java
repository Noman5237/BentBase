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

import java.util.Date;
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
	
	@Query ("select tag from Tag tag inner join GigTag gig_tag on tag.id = gig_tag.id.tagId and gig_tag.id.gigId = :gig_id")
	Page<Tag> getAllTags(@Param ("gig_id") Long gigId, Pageable pageable);
	
	@Query (nativeQuery = true, value = "select * from gig where gig.id in (select distinct gig.id from gig join gig_tag on gig.id = gig_tag.gig_id where gig_tag.tag_id in (select tag.id from tag where tag.name in :included_tags)) order by utl_match.edit_distance_similarity(gig.title, :title) desc")
	Page<Gig> filterGigs(@Param ("title") String title,
	                     @Param ("included_tags") String[] includedTags,
	                     Pageable pageable);
	
	
	@Query (nativeQuery = true, value = "select p_gig.get_total_earning(:gig_id, :start_date, :end_date) from dual")
	Long getTotalEarning(@Param ("gig_id") Long gig_id,
	                     @Param ("start_date") Date startDate,
	                     @Param ("end_date") Date endDate);
	
	@Override
	@Transactional
	void deleteById(Long id);
}