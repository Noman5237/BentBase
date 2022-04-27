package com.bentbase.backend.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
	
	@Override
	Page<Order> findAll(Pageable pageable);
	
	@Override
	Optional<Order> findById(Long id);
	
	Page<Order> findAllByGigId(Long gigId, Pageable pageable);
	
	@Transactional
	void deleteById(Long id);
}