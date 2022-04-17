package com.bentbase.backend.seller;

import com.bentbase.backend.application.Application;
import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.gig.Gig;
import com.bentbase.backend.project.Project;
import com.bentbase.backend.user.User;
import com.bentbase.backend.utils.PatchUtil;
import com.bentbase.backend.utils.SortUtil;
import lombok.SneakyThrows;
import org.hibernate.cfg.NotYetImplementedException;
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
public class SellerServiceImpl implements SellerService {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);
	
	private final SellerRepository sellerRepository;
	
	public SellerServiceImpl(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Seller> getAllSellers(Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), User.class));
			return sellerRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Seller.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Seller getSellerByEmail(String email) {
		Optional<Seller> seller = sellerRepository.findByEmail(email);
		if (seller.isEmpty()) {
			throw new GetException(Seller.class).withError("email", "does not exist");
		}
		
		return seller.get();
	}
	
	@SneakyThrows
	@Override
	public Page<Gig> getGigs(String email, Paginate paginate) {
		PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), Gig.class));
		return sellerRepository.getAllGigs(email, pagingSort);
	}
	
	@SneakyThrows
	@Override
	public Page<Application> getApplications(String email, Paginate paginate) {
		PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), Application.class));
		return sellerRepository.getAllApplications(email, pagingSort);
	}
	
	@SneakyThrows
	@Override
	public Seller createSeller(Seller seller) {
		Optional<Seller> existingUser = sellerRepository.findByEmail(seller.getEmail());
		if (existingUser.isPresent()) {
			throw new CreateException(Seller.class).withError("email", "already exists");
		}
		
		try {
			return sellerRepository.save(seller);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Seller.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Seller updateSeller(Map<String, Object> properties) {
		if (!properties.containsKey("email")) {
			throw new GetException(Seller.class).withError("email", "must not be blank");
		}
		
		var email = (String) properties.get("email");
		var seller = this.getSellerByEmail(email);
		
		try {
			PatchUtil.update(seller, properties);
		} catch (RESTException exception) {
			throw new UpdateException(Seller.class, exception);
		}
		
		return sellerRepository.save(seller);
	}
	
	@Override
	public void applyForProject(Project project) {
		throw new NotYetImplementedException();
	}
	
	@Override
	public void deleteSellerByEmail(String email) {
		this.getSellerByEmail(email);
		sellerRepository.deleteByEmail(email);
	}
}
