package com.bentbase.backend.application;

import com.bentbase.backend.seller.Seller;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table (name = "application")
public class Application {
	
	@Id
	@Column (nullable = false)
	private Long id;
	
	@Lob
	@Column
	private String coverLetter;
	
//	@ManyToOne (fetch = FetchType.EAGER)
//	@JoinColumn (name = "gig_id")
//	private Gig gig;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "seller_email")
	private Seller sellerEmail;
	
	public enum Status {
		PENDING, APPROVED
	}
	
	@Column
	private Status status;
}
