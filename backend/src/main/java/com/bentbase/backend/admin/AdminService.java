package com.bentbase.backend.admin;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AdminService {
	
	Page<Admin> getAllAdmins(Paginate paginate);
	
	Admin getAdminByEmail(String email);
	
	Admin createAdmin(Admin admin);
	
	Admin updateAdmin(Map<String, Object> properties);
	
	void deleteAdminByEmail(String email);
}
