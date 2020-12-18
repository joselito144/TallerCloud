package co.com.tallercloud.project.infraestructure.services.gateway;

import co.com.tallercloud.project.domain.model.ProjectDao;
import java.util.*;

public interface ProjectService {

    ProjectDao createProject(ProjectDao project);
    ProjectDao getProject(String projectIdentifier);
    List<ProjectDao> getAllProject();



}
