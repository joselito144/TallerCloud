package co.com.tallercloud.project.services;

import co.com.tallercloud.project.domain.Project;
import co.com.tallercloud.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getProyectsAll() {
        return projectRepository.findAll();
    }


    @Override
    public Project getProyect(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project getProjectByIdenfier(String projectIdenfier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdenfier);
        if( project != null) {
            return project;
        }
        return projectRepository.findByProjectIdentifier(projectIdenfier);
    }


}
