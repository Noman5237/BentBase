package com.bentbase.backend.project.tag;

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
public class ProjectTagId implements Serializable {
	
	private static final long serialVersionUID = 3817027669563773115L;
	
	@Column (name = "project_id", nullable = false)
	private Long projectId;
	
	@Column (name = "tag_id", nullable = false)
	private Long tagId;
	
	@Override
	public int hashCode() {
		return Objects.hash(tagId, projectId);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		ProjectTagId entity = (ProjectTagId) o;
		return Objects.equals(this.tagId, entity.tagId) && Objects.equals(this.projectId, entity.projectId);
	}
}