package com.bentbase.backend.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator (name = "order_id_generator", sequenceName = "order_id_sequence", allocationSize = 1)
@Table (name = "order_info")
public class Order {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "order_id_generator")
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
	@NotNull
	private Long gigId;
	
	@Column
	@NotNull
	private Long projectId;
	
	@Column
	private Long status;
}