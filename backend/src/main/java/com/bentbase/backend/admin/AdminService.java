package com.bentbase.backend.admin;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
	
	Page<Admin> getAllAdmins(Paginate paginate);
	
	Admin getAdminByEmail(String email);
	
	Admin updateAdmin(Admin admin);
	
	void deleteAdminByEmail(String email);
}
