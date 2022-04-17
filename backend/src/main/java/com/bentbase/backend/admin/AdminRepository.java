package com.bentbase.backend.admin;

import com.bentbase.backend.seller.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Seller> {
	
	@Override
	Page<Admin> findAll(Pageable pageable);
	
	Optional<Admin> findByEmail(String email);
	
	@Transactional
	void deleteByEmail(String email);
}