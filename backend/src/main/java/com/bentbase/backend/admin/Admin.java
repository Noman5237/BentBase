package com.bentbase.backend.admin;

import com.bentbase.backend.user.rest.User;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table (name = "admin")
public class Admin {
	
	@Id
	@Column (nullable = false)
	private String userEmail;
	
	@OneToOne (optional = false, orphanRemoval = true)
	@JoinColumn (name = "user_email", nullable = false)
	private User user;
}
