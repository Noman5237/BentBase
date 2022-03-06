package com.bentbase.backend.seller;

import com.bentbase.backend.user.User;
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
	@Column (name = "userEmail", nullable = false)
	private String userEmail;
	
	@OneToOne (optional = false, orphanRemoval = true)
	@JoinColumn (name = "userEmail", nullable = false)
	private User user;

//	appliedJobs
//	approvedJobs
//	gigs
//	reviews
}
