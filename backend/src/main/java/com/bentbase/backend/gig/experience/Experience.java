package com.bentbase.backend.gig.experience;

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
@SequenceGenerator (name = "experience_id_generator", sequenceName = "experience_id_sequence", allocationSize = 1)
@Table (name = "experience")
public class Experience {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "experience_id_generator")
	@Column (nullable = false)
	private Long id;
	
	@Column
	@NotBlank
	private String jobTitle;
	
	@Column
	@NotBlank
	private String workplace;
	
	@Column
	@NotBlank
	private String location;
	
	@Column
	@NotNull
	private LocalDate startTime;
	
	@Column
	private LocalDate endTime;
	
	@Lob
	@Column
	@NotBlank
	private String description;
	
	@Column
	@NotNull
	private Long gigId;
}