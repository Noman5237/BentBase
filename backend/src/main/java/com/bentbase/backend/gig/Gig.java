package com.bentbase.backend.gig;

import com.bentbase.backend.gig.education.Education;
import com.bentbase.backend.gig.experience.Experience;
import com.bentbase.backend.tag.Tag;

import java.util.List;

public class Gig {
	
	private String id;
	private String title;
	private String about;
	
	private List<Tag> tags;
	private List<Experience> experiences;
	private List<Education> education;
}
