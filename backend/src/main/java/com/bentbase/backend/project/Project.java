package com.bentbase.backend.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator (name = "project_id_generator", sequenceName = "project_id_sequence", allocationSize = 1)
@Table (name = "project")
public class Project {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "project_id_generator")
	@Column (nullable = false)
	private Long id;
	
	@Column (nullable = false)
	private String title;
	
	@Column
	private Long deadline;
	
	@Column
	private Long budget;
	
	@Column
	private LocalDate postTime;
	
	@Column
	private Long status;
	
	@Column (nullable = false)
	private String buyerEmail;
}
