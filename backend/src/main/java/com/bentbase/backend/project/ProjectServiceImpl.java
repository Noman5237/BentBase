package com.bentbase.backend.project;

import com.bentbase.backend.application.Application;
import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
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
public class ProjectServiceImpl implements ProjectService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	private final ProjectRepository projectRepository;
	
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Project> getAllProjects(Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(),
			                                        paginate.getSize(),
			                                        SortUtil.getOrdersFromStringArray(paginate.getSorts(),
			                                                                          Project.class));
			return projectRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Project.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Project getProjectById(Long id) {
		Optional<Project> project = projectRepository.findById(id);
		if (project.isEmpty()) {
			throw new GetException(Project.class).withError("id", "does not exist");
		}
		
		return project.get();
	}
	
	@Override
	public Page<Project> searchProjects(String title, List<Tag> tags, Paginate paginate) {
		return null;
	}
	
	@SneakyThrows
	@Override
	public Page<Application> getApplications(Long id, Paginate paginate) {
		PageRequest pagingSort = PageRequest.of(paginate.getPage(),
		                                        paginate.getSize(),
		                                        SortUtil.getOrdersFromStringArray(paginate.getSorts(),
		                                                                          Application.class));
		return projectRepository.getAllApplications(id, pagingSort);
	}
	
	@SneakyThrows
	@Override
	public Page<Tag> getTags(Long projectId, Paginate paginate) {
		this.getProjectById(projectId);
		PageRequest pagingSort = PageRequest.of(paginate.getPage(),
		                                        paginate.getSize(),
		                                        SortUtil.getOrdersFromStringArray(paginate.getSorts(), Tag.class));
		return projectRepository.getAllTags(projectId, pagingSort);
	}
	
	@SneakyThrows
	@Override
	public Project createProject(Project project) {
		try {
			return projectRepository.save(project);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Project.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Project updateProject(Map<String, Object> properties) {
		if (!properties.containsKey("id")) {
			throw new GetException(Project.class).withError("id", "must not be blank");
		}
		
		var id = Long.valueOf((Integer) properties.get("id"));
		var project = this.getProjectById(id);
		
		try {
			PatchUtil.update(project, properties);
		} catch (RESTException exception) {
			throw new UpdateException(Project.class, exception);
		}
		
		return projectRepository.save(project);
	}
	
	@Override
	public void deleteProjectById(Long id) {
		this.getProjectById(id);
		projectRepository.deleteById(id);
	}
}
