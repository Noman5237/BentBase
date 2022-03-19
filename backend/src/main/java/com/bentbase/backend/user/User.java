package com.bentbase.backend.user;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table (name = "\"user\"")
public class User {
	
	@Id
	@Column (nullable = false)
	private String email;
	
	@Column
	@NotBlank (message = "First name should not be blank")
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private Date dob;

//	image
}
