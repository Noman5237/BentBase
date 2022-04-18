package com.bentbase.backend.project;

import com.bentbase.backend.buyer.Buyer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "project")
public class Project {
	
	@Id
	@Column (nullable = false)
	private Long id;
	
	@Column (nullable = false)
	private String title;
	
	@Column
	private Long deadline;
	
	@Column
	private Long budget;
	
	@Column
	private LocalDate postTime;
	
	@Column
	private Long status;
	
	@Column (nullable = false)
	private String buyerEmail;
}
