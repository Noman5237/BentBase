package com.bentbase.backend.buyer;

import com.bentbase.backend.project.Project;
import com.bentbase.backend.review.Review;
import com.bentbase.backend.user.rest.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table (name = "buyer")
public class Buyer {
	
	@Id
	@Column (nullable = false)
	private String userEmail;
	
	@OneToOne (optional = false, orphanRemoval = true)
	@JoinColumn (name = "user_email", nullable = false)
	private User user;
	
	private List<Project> projects;
	private List<Review> reviews;
}
