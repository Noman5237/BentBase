package com.bentbase.backend.project;

import com.bentbase.backend.tag.Tag;
import com.bentbase.backend.utils.PageUtil.Paginate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.persistence.Table;
import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@Builder
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
	
	enum Status {
		IN_REVIEW, IN_PROGRESS, COMPLETED
	}
	
	private Status status;
	
}
