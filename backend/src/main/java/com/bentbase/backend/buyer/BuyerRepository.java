package com.bentbase.backend.buyer;

import com.bentbase.backend.seller.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, String>, JpaSpecificationExecutor<Buyer> {
	
	@Override
	Page<Buyer> findAll(Pageable pageable);
	
	Optional<Buyer> findByEmail(String email);
	
	@Transactional
	void deleteByEmail(String email);
}