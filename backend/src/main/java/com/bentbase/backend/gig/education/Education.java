package com.bentbase.backend.gig.education;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SequenceGenerator (name = "education_id_generator", sequenceName = "education_id_sequence", allocationSize = 1)
@Table (name = "education")
public class Education {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "education_id_generator")
	@Column (nullable = false)
	private Long id;
	
	@Column
	@NotBlank
	private String institution;
	
	@Column
	@NotBlank
	private String degree;
	
	@Column
	@NotBlank
	private String major;
	
	@Column
	@NotNull
	private LocalDate startYear;
	
	@Column
	@NotNull
	private LocalDate endYear;
	
	@Column
	@NotNull
	private Long gigId;
}
