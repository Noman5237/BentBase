package com.bentbase.backend.seller;

import com.bentbase.backend.application.Application;
import com.bentbase.backend.gig.Gig;
import com.bentbase.backend.project.Project;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public interface SellerService {
	
	Page<Seller> getAllSellers(Paginate paginate);
	
	Seller getSellerByEmail(String email);
	
	Page<Gig> getGigs(String email, Paginate paginate);
	
	Page<Application> getApplications(String email, Paginate paginate);
	
	Long getTotalEarning(String email, Date startDate, Date endDate);
	
	Seller createSeller(Seller seller);
	
	Seller updateSeller(Map<String, Object> properties);
	
	void deleteSellerByEmail(String email);
}
