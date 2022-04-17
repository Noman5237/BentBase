package com.bentbase.backend.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
@Table (name = "tag")
public class Tag {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	@Column (nullable = false)
	private Long id;
	
	@Column
	@NotBlank
	private String name;
	
	@Column
	@NotBlank
	private String description;
}
