package com.bentbase.backend.application;

import com.bentbase.backend.seller.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String>, JpaSpecificationExecutor<Seller> {
	
	@Override
	Page<Application> findAll(Pageable pageable);
	
	Optional<Application> findById(Long id);
	
	@Transactional
	void deleteById(Long id);
}