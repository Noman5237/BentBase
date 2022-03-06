package com.bentbase.backend.buyer;

import com.bentbase.backend.user.User;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table (name = "buyer")
public class Buyer {
	
	@Id
	@Column (name = "userEmail", nullable = false)
	private String userEmail;
	
	@OneToOne (optional = false, orphanRemoval = true)
	@JoinColumn (name = "userEmail", nullable = false)
	private User user;
	
//	jobs
//	reviews
}
