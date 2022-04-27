package com.bentbase.backend.project.tag;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "project_tag")
public class ProjectTag {
	
	@EmbeddedId
	private ProjectTagId id;
}