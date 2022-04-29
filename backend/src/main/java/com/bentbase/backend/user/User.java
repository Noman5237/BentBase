package com.bentbase.backend.user;

import com.bentbase.backend.core.exception.constraint.Message;
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
	@Email (message = Message.EMAIL)
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
	
	@Column
	private String image;
}
