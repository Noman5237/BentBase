package com.bentbase.backend.seller;

import com.bentbase.backend.gig.Gig;
import com.bentbase.backend.project.Project;

public interface SellerService {
	
	void createGig(Gig gig);
	
	void updateGig(Gig gig);
	
	void deleteGigById(String id);
	
	void applyForJob(Project project);
	
	void deleteJobApplicationById(String id);
}
