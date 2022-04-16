package com.bentbase.backend.gig.education;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table (name = "education")
public class Education {
	
	@Id
	@Column (nullable = false)
	private Long id;
	
	@Column
	private String institution;
	
	
	private String degree;
	private String major;
	private LocalDate startYear;
	private LocalDate endYear;
}
