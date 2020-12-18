package co.com.tallercloud.project.infraestructure.services.gateway;

import co.com.tallercloud.project.domain.model.ProjectTaskDao;

import java.util.List;

public interface ProjectTaskService {

    ProjectTaskDao createTask(ProjectTaskDao projectTask);
    List<ProjectTaskDao> getByProjectIdentifier(String projectIdentifier);
    Double getTotalHoursByProjectIdentifier(String projectIdentifier);
    Double getTotalHoursByProjectIdentifierStatus(String projectIdentifier, String  status);
    ProjectTaskDao deleteStatusTask(String projectIdentifier, Long  task);



}
