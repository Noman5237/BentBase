package com.bentbase.backend.admin;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.user.User;
import com.bentbase.backend.utils.PatchUtil;
import com.bentbase.backend.utils.SortUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.Map;
import java.util.Optional;

import static com.bentbase.backend.utils.PageUtil.Paginate;

@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	private final AdminRepository adminRepository;
	
	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Admin> getAllAdmins(Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), User.class));
			return adminRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Admin.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Admin getAdminByEmail(String email) {
		Optional<Admin> admin = adminRepository.findByEmail(email);
		if (admin.isEmpty()) {
			throw new GetException(Admin.class).withError("email", "does not exist");
		}
		
		return admin.get();
	}
	
	@SneakyThrows
	@Override
	public Admin createAdmin(Admin admin) {
		Optional<Admin> existingUser = adminRepository.findByEmail(admin.getEmail());
		if (existingUser.isPresent()) {
			throw new CreateException(Admin.class).withError("email", "already exists");
		}
		
		try {
			return adminRepository.save(admin);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Admin.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Admin updateAdmin(Map<String, Object> properties) {
		if (!properties.containsKey("email")) {
			throw new GetException(Admin.class).withError("email", "must not be blank");
		}
		
		var email = (String) properties.get("email");
		var admin = this.getAdminByEmail(email);
		
		try {
			PatchUtil.update(admin, properties);
		} catch (RESTException exception) {
			throw new UpdateException(Admin.class, exception);
		}
		
		return adminRepository.save(admin);
	}
	
	@Override
	public void deleteAdminByEmail(String email) {
		this.getAdminByEmail(email);
		adminRepository.deleteByEmail(email);
	}
}
