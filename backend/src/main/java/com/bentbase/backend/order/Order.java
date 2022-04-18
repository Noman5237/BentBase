package com.bentbase.backend.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table (name = "order_info")
public class Order {
	
	@Id
	@Column (nullable = false)
	private Long id;
	
	@Column (precision = 9, scale = 3)
	private BigDecimal price;
	
	@Column (precision = 5, scale = 3)
	private BigDecimal tips;
	
	@Column
	private LocalDate dateOfOrder;
	
	@Column
	private LocalDate dateOfDelivery;
	
	@Column
	private Long gigId;
	
	@Column
	private String sellerEmail;
	
	@Column
	private Long projectId;
}