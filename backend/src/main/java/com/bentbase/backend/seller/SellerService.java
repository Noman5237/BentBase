package com.bentbase.backend.seller;

import com.bentbase.backend.gig.Gig;
import com.bentbase.backend.project.Project;
import com.bentbase.backend.project.application.Application;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SellerService {
	
	Page<Seller> getAllSellers(Paginate paginate);
	
	Seller getSellerByEmail(String email);
	
	Page<Application> getApplications(Seller seller, Paginate paginate);
	
	Page<Application> getApplications(Seller seller, Application.Status status, Paginate paginate);
	
	Page<Gig> getGigs();
	
	Seller createSeller(Seller seller);
	
	Seller updateSeller(Map<String, Object> properties);
	
	void applyForProject(Project project);
	
	void deleteSellerByEmail(String email);
}
