package co.com.tallercloud.projecttask.infraestructure.services.gateway;

import co.com.tallercloud.projecttask.domain.model.ProjectTaskDao;

import java.util.List;

public interface ProjectTaskService {

    ProjectTaskDao createTask(ProjectTaskDao projectTask);
    List<ProjectTaskDao> getByProjectIdentifier(String projectIdentifier);
    Double getTotalHoursByProjectIdentifier(String projectIdentifier);
    Double getTotalHoursByProjectIdentifierStatus(String projectIdentifier, String  status);
    ProjectTaskDao deleteStatusTask(String projectIdentifier, Long  task);



}
