package com.bentbase.backend.application.status;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SequenceGenerator (name = "application_status_id_generator", sequenceName = "application_status_id_sequence", allocationSize = 1)
@Table (name = "application_status")
public class ApplicationStatus {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "application_status_id_generator")
	@Column (nullable = false)
	private Long id;
	
	@Column (unique = true, length = 50)
	@NotBlank
	private String name;
	
	@Column (length = 512)
	@NotBlank
	private String description;
}
