package com.bentbase.backend.project.status;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.user.User;
import com.bentbase.backend.user.UserServiceImpl;
import com.bentbase.backend.utils.PageUtil;
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

@Service
public class ProjectStatusServiceImpl implements ProjectStatusService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final ProjectStatusRepository projectStatusRepository;
	
	public ProjectStatusServiceImpl(ProjectStatusRepository projectStatusRepository) {
		this.projectStatusRepository = projectStatusRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<ProjectStatus> getAllProjectStatuses(PageUtil.Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(),
			                                        paginate.getSize(),
			                                        SortUtil.getOrdersFromStringArray(paginate.getSorts(),
			                                                                          ProjectStatus.class));
			return projectStatusRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(ProjectStatus.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public ProjectStatus getProjectStatusById(Long id) {
		Optional<ProjectStatus> projectStatus = projectStatusRepository.findById(id);
		if (projectStatus.isEmpty()) {
			throw new GetException(ProjectStatus.class).withError("id", "does not exist");
		}
		
		return projectStatus.get();
	}
	
	@SneakyThrows
	@Override
	public ProjectStatus getProjectStatusByName(String name) {
		Optional<ProjectStatus> projectStatus = projectStatusRepository.findByName(name);
		if (projectStatus.isEmpty()) {
			throw new GetException(ProjectStatus.class).withError("name", "does not exist");
		}
		
		return projectStatus.get();
	}
	
	@SneakyThrows
	@Override
	public ProjectStatus createProjectStatus(ProjectStatus projectStatus) {
		Optional<ProjectStatus> existingProjectStatus = projectStatusRepository.findByName(projectStatus.getName());
		if (existingProjectStatus.isPresent()) {
			throw new CreateException(ProjectStatus.class).withError("name", "already exists");
		}
		
		try {
			return projectStatusRepository.save(projectStatus);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(ProjectStatus.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public ProjectStatus updateProjectStatus(Map<String, Object> properties) {
		if (!properties.containsKey("id") && !properties.containsKey("name")) {
			throw new GetException(ProjectStatus.class).withError("id", "must not be blank")
			                                           .withError("name", "must not be blank");
		}
		
		var projectStatus = properties.containsKey("id") ? getProjectStatusById(Long.valueOf((Integer) properties.get(
				"id"))) : getProjectStatusByName((String) properties.get("name"));
		
		try {
			PatchUtil.update(projectStatus, properties);
		} catch (RESTException exception) {
			throw new UpdateException(User.class, exception);
		}
		
		return projectStatusRepository.save(projectStatus);
	}
	
	@Override
	public void deleteProjectStatusById(Long id) {
		this.getProjectStatusById(id);
		projectStatusRepository.deleteById(id);
	}
	
	@Override
	public void deleteProjectStatusByName(String name) {
		this.getProjectStatusByName(name);
		projectStatusRepository.deleteByName(name);
	}
}
