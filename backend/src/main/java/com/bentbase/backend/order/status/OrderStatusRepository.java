package com.bentbase.backend.order.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long>, JpaSpecificationExecutor<OrderStatus> {
	
	@Override
	Page<OrderStatus> findAll(Pageable pageable);
	
	@Override
	Optional<OrderStatus> findById(Long id);
	
	Optional<OrderStatus> findByName(String name);
	
	@Transactional
	void deleteByName(String name);
}
