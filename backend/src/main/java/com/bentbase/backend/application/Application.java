package com.bentbase.backend.application;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SequenceGenerator (name = "application_id_generator", sequenceName = "application_id_sequence", allocationSize = 1)
@Table (name = "application")
public class Application {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "application_id_generator")
	@Column (nullable = false)
	private Long id;
	
	@Lob
	@Column
	@NotBlank
	private String coverLetter;
	
	@Column
	@NotNull
	private Long status;
	
	@Column
	@NotNull
	private Long gigId;
	
	@Column
	@NotNull
	private String sellerEmail;
	
	@Column
	@NotNull
	private Long projectId;
}
