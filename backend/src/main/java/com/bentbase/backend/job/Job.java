package com.bentbase.backend.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@AllArgsConstructor
@Getter
@Setter
public class Job {
	
	private String id;
	private String jobTitle;
//	skills
	private String description;
//	deadline
	private long budget;
	private Time postTime;
}
