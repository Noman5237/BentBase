package com.bentbase.backend.seller;

import com.bentbase.backend.user.rest.User;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table (name = "seller")
public class Seller {
	
	@Id
	@Column (nullable = false)
	private String userEmail;
	
	@OneToOne (optional = false, orphanRemoval = true)
	@JoinColumn (name = "user_email", nullable = false)
	private User user;

//	appliedJobs
//	approvedJobs
//	gigs
//	reviews
}
