package com.bentbase.backend.project.status;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SequenceGenerator (name = "project_status_id_generator", sequenceName = "project_status_id_sequence", allocationSize = 1)
@Table (name = "project_status")
public class ProjectStatus {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "project_status_id_generator")
	@Column (nullable = false)
	private Long id;
	
	@Column (unique = true, length = 50)
	@NotBlank
	private String name;
	
	@Column (length = 512)
	@NotBlank
	private String description;
}
