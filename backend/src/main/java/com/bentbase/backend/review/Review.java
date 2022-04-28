package com.bentbase.backend.review;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SequenceGenerator (name = "review_id_generator", sequenceName = "review_id_sequence", allocationSize = 1)
@Table (name = "review")
public class Review {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "review_id_generator")
	@Column
	private Long id;
	
	@Column
	@NotNull
	private float rating;
	
	@Column
	private String comments;
	
	@Column
	@NotBlank
	private String reviewer;
}
