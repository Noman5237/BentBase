package com.bentbase.backend.project;

import com.bentbase.backend.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Table (name = "project")
public class Project {
	
	private String id;
	private String title;
	private List<Tag> tags;
	private String description;
	private int deadline;
	private long budget;
	private Time postTime;
	private int status;
	
	private List<Application> applications;
}
