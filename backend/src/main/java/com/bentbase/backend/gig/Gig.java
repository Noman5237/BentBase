package com.bentbase.backend.gig;

import com.bentbase.backend.project.Project;
import com.bentbase.backend.tag.Tag;

import java.util.List;

public class Gig {
	
	private String id;
	private String title;
	private String about;
	private List<Tag> tags;
	private List<Experience> experiences;
	private List<Education> educations;
	private List<Project> projects;
}
