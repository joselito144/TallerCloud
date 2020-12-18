package co.com.tallercloud.projecttask.infraestructure.services;

import co.com.tallercloud.projecttask.domain.entities.ProjectTask;
import co.com.tallercloud.projecttask.domain.model.ProjectTaskDao;
import co.com.tallercloud.projecttask.infraestructure.controller.java.models.util.ProjectTaskConstants;
import co.com.tallercloud.projecttask.infraestructure.services.gateway.ProjectTaskService;
import co.com.tallercloud.projecttask.infraestructure.services.repository.ProjectTaskRepository;
import co.com.tallercloud.projecttask.domain.model.ProjectExceptionBadRequest;
import co.com.tallercloud.projecttask.domain.model.ProjectExceptionNotFound;
import co.com.tallercloud.projecttask.domain.model.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectTaskServiceImpl implements ProjectTaskService {


    @Autowired
    private final ProjectTaskRepository projectTaskRepository;

    @Override
    public ProjectTaskDao createTask(ProjectTaskDao projectTask) {
        try {
            ProjectTask projectTaskDB = new ProjectTask();
            BeanUtils.copyProperties(projectTask,projectTaskDB);
            projectTaskDB= projectTaskRepository.save(projectTaskDB);
            BeanUtils.copyProperties(projectTaskDB,projectTask);
            return  projectTask;
        }catch (DataIntegrityViolationException exception){
             return structureError();
        }

    }

    private ProjectTaskDao structureError(){
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setErrorCode(ProjectTaskConstants.CODE400);
        statusResponse.setErrorMessage(ProjectTaskConstants.ERROR);
        statusResponse.setErrorDesc(ProjectTaskConstants.ERRORDESC);
        statusResponse.setModule(ProjectTaskConstants.MODULE);
        throw new ProjectExceptionBadRequest(statusResponse);
    }

    @Override
    public List<ProjectTaskDao> getByProjectIdentifier(String projectIdentifier){
        try{
            List<ProjectTask> task= projectTaskRepository.findByProjectIdentifier(projectIdentifier);
            List<ProjectTaskDao> listDao =new ArrayList<>();
            if(!StringUtils.isEmpty(task) && !task.isEmpty()){
                task.forEach(copy->{
                    ProjectTaskDao projectTaskDao = new ProjectTaskDao();
                    BeanUtils.copyProperties(copy,projectTaskDao);
                    listDao.add(projectTaskDao);
                });
               /*Backlog backlog=getBackLog(projectIdentifier);
                task.forEach(projectTask ->
                    projectTask.setBacklog(backlog));*/
                return listDao;
            }else{
                 return responseError();
            }
        }catch (DataIntegrityViolationException e){
            return responseError();
        }

    }

    @Override
    public Double getTotalHoursByProjectIdentifier(String projectIdentifier){

        List<ProjectTask> totalHours=projectTaskRepository.findByProjectIdentifier(projectIdentifier);
        if(!totalHours.isEmpty()) {
            Double totalHoursProjects = 0.0;
            for (ProjectTask hours : totalHours) {
                if (!ProjectTaskConstants.DELETED.equals(hours.getStatus())) {
                    totalHoursProjects += hours.getHours();
                }
            }
            return totalHoursProjects;
        }else{
            responseError();
            return 0.0;
        }

    }

    @Override
    public Double getTotalHoursByProjectIdentifierStatus(String projectIdentifier, String status){

        List<ProjectTask> totalHours=projectTaskRepository.findByProjectIdentifier(projectIdentifier);
        if(!totalHours.isEmpty()) {
            Double totalHoursProjects = 0.0;
            for (ProjectTask hours : totalHours) {
                if (status.equals(hours.getStatus())) {
                    totalHoursProjects += hours.getHours();
                }
            }
            return totalHoursProjects;
        }else{
            responseError();
            return 0.0;
        }

    }

    @Override
    public ProjectTaskDao deleteStatusTask(String projectIdentifier, Long task) {
        ProjectTask projectTask;
        ProjectTaskDao projectTaskDao = new ProjectTaskDao();
        try {
            Optional<ProjectTask> deleteTask = projectTaskRepository.findById(task);
            if (deleteTask.isPresent()) {
                deleteTask.get().setStatus(ProjectTaskConstants.DELETED);
                projectTask=projectTaskRepository.save(deleteTask.get());
                BeanUtils.copyProperties(projectTask,projectTaskDao);
                return projectTaskDao;
            } else {
                return structureError();
            }
        }catch (DataIntegrityViolationException e){
            return  structureError();
        }

    }
    /*private Backlog getBackLog(String projecIdentifier){
        ResponseEntity<Backlog> backlog = backLogClient.getBacklog(projecIdentifier);
        if(backlog.getStatusCode().is2xxSuccessful()){
            return backlog.getBody();
        }else{
            return  new Backlog();
        }
    }*/


    private List<ProjectTaskDao> responseError(){
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setErrorCode("404");
        statusResponse.setErrorMessage("Se ha presentando un error consultando en la base de datos");
        statusResponse.setErrorDesc("No se han encontrado datos para el projecto");
        statusResponse.setModule("projectask");
        throw new ProjectExceptionNotFound(statusResponse);
    }

}
