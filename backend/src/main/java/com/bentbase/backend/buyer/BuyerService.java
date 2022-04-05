package com.bentbase.backend.buyer;

import com.bentbase.backend.project.Application;
import com.bentbase.backend.project.Project;
import com.bentbase.backend.seller.Seller;
import com.bentbase.backend.utils.PageUtil;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

public interface BuyerService {
	
	Page<Buyer> getAllBuyers(Paginate paginate);
	
	void createProject(Project project);
	
	void deleteProjectById(String id);
	
	Seller approveApplication(Application application);
}
