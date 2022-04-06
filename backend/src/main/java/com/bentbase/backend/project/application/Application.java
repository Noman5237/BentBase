package com.bentbase.backend.project.application;

import com.bentbase.backend.gig.Gig;
import com.bentbase.backend.seller.Seller;

public class Application {
	
	private String id;
	private Seller applicant;
	private Gig gig;
	private String coverLetter;
	
	public enum Status {
		PENDING,
		APPROVED
	}
	
	private Status status;
	
}
