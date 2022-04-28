package com.bentbase.backend.user.review;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class UserReviewId implements Serializable {
	
	private static final long serialVersionUID = -6231767747721378681L;
	
	@Column (name = "user_email", nullable = false)
	private String userEmail;
	
	@Column (name = "review_id", nullable = false)
	private Long reviewId;
	
	@Override
	public int hashCode() {
		return Objects.hash(userEmail, reviewId);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		UserReviewId entity = (UserReviewId) o;
		return Objects.equals(this.userEmail, entity.userEmail) && Objects.equals(this.reviewId, entity.reviewId);
	}
}