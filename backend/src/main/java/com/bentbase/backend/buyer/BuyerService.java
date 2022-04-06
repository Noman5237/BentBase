package com.bentbase.backend.buyer;

import com.bentbase.backend.project.Project;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

public interface BuyerService {
	
	Page<Buyer> getAllBuyers(Paginate paginate);
	
	Buyer getBuyerByEmail(String email);
	
	Page<Project> getProjects(Buyer buyer, Paginate paginate);
	
	void deleteBuyerByEmail(String email);
}
