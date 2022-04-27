package com.bentbase.backend.gig.tag;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "gig_tag")
public class GigTag {
	
	@EmbeddedId
	private GigTagId id;
}