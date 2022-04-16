package com.bentbase.backend.user.rest;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Inheritance (strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
@Entity
@Table (name = "\"user\"")
public class User implements Serializable {
	
	@Id
	@Column (nullable = false)
	@Email (message = "email address must be valid")
	protected String email;
	
	@Column
	@NotBlank
	protected String firstName;
	
	@Column
	@NotBlank
	protected String lastName;
	
	//	FIXME: Add a date constraint of at least 12 years
	@Column
	@NotNull
	protected Date dob;
	
	@Column
	protected String image;
}
