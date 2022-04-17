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
@ToString
@Entity
@SequenceGenerator (name = "tag_id_generator", sequenceName = "tag_id_sequence", allocationSize = 1)
@Table (name = "tag")
public class Tag {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "tag_id_generator")
	@Column (nullable = false)
	private Long id;
	
	@Column (unique = true, length = 50)
	@NotBlank
	private String name;
	
	@Column (length = 512)
	@NotBlank
	private String description;
}
