package com.bentbase.backend.seller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String>, JpaSpecificationExecutor<Seller> {
	
	@Override
	Page<Seller> findAll(Pageable pageable);
	
	Optional<Seller> findByEmail(String email);
	
	@Transactional
	void deleteUserByEmail(String email);
}