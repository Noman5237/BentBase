package com.bentbase.backend.order.status;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SequenceGenerator (name = "order_status_id_generator", sequenceName = "order_status_id_sequence", allocationSize = 1)
@Table (name = "order_status")
public class OrderStatus {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "order_status_id_generator")
	@Column (nullable = false)
	private Long id;
	
	@Column (unique = true, length = 50)
	@NotBlank
	private String name;
	
	@Column (length = 512)
	@NotBlank
	private String description;
}
