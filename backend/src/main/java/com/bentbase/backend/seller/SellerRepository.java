package com.bentbase.backend.seller;

import com.bentbase.backend.application.Application;
import com.bentbase.backend.gig.Gig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String>, JpaSpecificationExecutor<Seller> {
	
	@Override
	Page<Seller> findAll(Pageable pageable);
	
	Optional<Seller> findByEmail(String email);
	
	@Query ("select g from Gig g where g.seller_email = :email")
	Page<Gig> getAllGigs(@Param ("email") String email, Pageable pageable);
	
	@Query ("select a from Application a where a.sellerEmail = :email")
	Page<Application> getAllApplications(@Param ("email") String email, Pageable pageable);
	
	@Query (nativeQuery = true, value = "select p_seller.get_total_earning(:seller_email, :start_date, :end_date) from dual")
	Long getTotalEarning(@Param ("seller_email") String seller_email,
	                     @Param ("start_date") Date startDate,
	                     @Param ("end_date") Date endDate);
	
	@Transactional
	void deleteByEmail(String email);
}