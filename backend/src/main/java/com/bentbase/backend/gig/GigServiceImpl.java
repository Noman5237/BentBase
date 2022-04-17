package com.bentbase.backend.gig;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.gig.education.Education;
import com.bentbase.backend.gig.experience.Experience;
import com.bentbase.backend.tag.Tag;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.bentbase.backend.utils.PageUtil.Paginate;

@Service
public class GigServiceImpl implements GigService {
	
	private static final Logger logger = LoggerFactory.getLogger(GigServiceImpl.class);
	
	private final GigRepository gigRepository;
	
	public GigServiceImpl(GigRepository gigRepository) {
		this.gigRepository = gigRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Gig> getAllGigs(Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), Gig.class));
			return gigRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Gig.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Gig getGigById(Long id) {
		Optional<Gig> gig = gigRepository.findById(id);
		if (gig.isEmpty()) {
			throw new GetException(Gig.class).withError("id", "does not exist");
		}
		
		return gig.get();
	}
	
	@Override
	public Page<Gig> searchGigs(String title, List<Tag> tags, Paginate paginate) {
		return null;
	}
	
	@SneakyThrows
	@Override
	public Page<Education> getEducations(Long gigId, Paginate paginate) {
		this.getGigById(gigId);
		PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), Education.class));
		return gigRepository.getAllEducations(gigId, pagingSort);
	}
	
	@SneakyThrows
	@Override
	public Page<Experience> getExperiences(Long gigId, Paginate paginate) {
		this.getGigById(gigId);
		PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), Experience.class));
		return gigRepository.getAllExperiences(gigId, pagingSort);
	}
	
	@SneakyThrows
	@Override
	public Gig createGig(Gig gig) {
		try {
			return gigRepository.save(gig);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Gig.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Gig updateGig(Map<String, Object> properties) {
		if (!properties.containsKey("id")) {
			throw new GetException(Gig.class).withError("id", "must not be blank");
		}
		
		var id = Long.valueOf((Integer) properties.get("id"));
		var gig = this.getGigById(id);
		
		try {
			PatchUtil.update(gig, properties);
		} catch (RESTException exception) {
			throw new UpdateException(Gig.class, exception);
		}
		
		return gigRepository.save(gig);
	}
	
	@Override
	public void deleteGigById(Long id) {
		this.getGigById(id);
		gigRepository.deleteById(id);
	}
}
