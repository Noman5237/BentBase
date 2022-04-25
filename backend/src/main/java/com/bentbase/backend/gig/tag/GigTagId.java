package com.bentbase.backend.gig.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class GigTagId implements Serializable {
	
	private static final long serialVersionUID = 3817027669563773115L;
	
	@Column (name = "gig_id", nullable = false)
	private Long gigId;
	
	@Column (name = "tag_id", nullable = false)
	private Long tagId;
	
	@Override
	public int hashCode() {
		return Objects.hash(tagId, gigId);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		GigTagId entity = (GigTagId) o;
		return Objects.equals(this.tagId, entity.tagId) && Objects.equals(this.gigId, entity.gigId);
	}
}