package com.bentbase.backend.project;

import com.bentbase.backend.project.application.Application;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table (name = "APPLICATION_STATUS")
public class ApplicationStatus {
	
	@Id
	@Column (name = "ID", nullable = false)
	private Long id;
	
	@Column (name = "NAME", length = 50)
	private String name;
	
	@Column (name = "DESCRIPTION", length = 512)
	private String description;
	
	@OneToMany (mappedBy = "status")
	private Set<Application> applications = new LinkedHashSet<>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<Application> getApplications() {
		return applications;
	}
	
	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}
	
}