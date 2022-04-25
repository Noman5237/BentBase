package com.bentbase.backend.tag;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
//	@Length (max = 50)
	private String name;
	
	@Column (length = 512)
	@NotBlank
//	@Length (max = 512)
	private String description;
}
