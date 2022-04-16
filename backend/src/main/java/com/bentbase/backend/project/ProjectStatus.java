package com.bentbase.backend.project;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "project_status")
public class ProjectStatus {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	@Column (name = "id", nullable = false)
	private Long id;
	
	@Column (name = "name", length = 50)
	private String name;
	
	@Column (name = "description", length = 512)
	private String description;
}