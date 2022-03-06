package com.bentbase.backend.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table (name = "user")
public class User {
	
	@Id
	@Column (name = "email", nullable = false)
	private String email;
	
	@Column (name = "firstName")
	private String firstName;
	
	@Column (name = "lastName")
	private String lastName;
	
	@Column (name = "dob")
	private Date dob;

//	image
}
