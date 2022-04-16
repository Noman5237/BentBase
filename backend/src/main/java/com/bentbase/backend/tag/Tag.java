package com.bentbase.backend.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name = "tag")
public class Tag {
	
	@Id
	private int id;
	
	@Column
	@NotBlank
	private String name;
	
	@Column
	@NotBlank
	private String description;
}
