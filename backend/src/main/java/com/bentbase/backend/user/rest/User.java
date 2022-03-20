package com.bentbase.backend.user.rest;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table (name = "\"user\"")
public class User {
	
	@Id
	@Column (nullable = false)
	@Email (message = "email address must be valid")
	private String email;
	
	@Column
	@NotBlank
	private String firstName;
	
	@Column
	@NotBlank
	private String lastName;
	
	//	FIXME: Add a date constraint of at least 12 years
	@Column
	@NotNull
	private Date dob;
	
	//	image
}
