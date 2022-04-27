package com.bentbase.backend.buyer;

import com.bentbase.backend.project.Project;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface BuyerService {
	
	Page<Buyer> getAllBuyers(Paginate paginate);
	
	Buyer getBuyerByEmail(String email);
	
	Page<Project> getProjects(Buyer buyer, Paginate paginate);
	
	Buyer createBuyer(Buyer buyer);
	
	Buyer updateBuyer(Map<String, Object> properties);
	
	void deleteBuyerByEmail(String email);
}
