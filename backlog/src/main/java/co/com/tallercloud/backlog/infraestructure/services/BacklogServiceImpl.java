package co.com.tallercloud.backlog.infraestructure.services;

import co.com.tallercloud.backlog.domain.entities.Backlog;
import co.com.tallercloud.backlog.infraestructure.controller.java.models.project.output.ProjectRS;
import co.com.tallercloud.backlog.infraestructure.controller.java.models.projectTask.output.ProjectTaskRS;
import co.com.tallercloud.backlog.infraestructure.controller.java.models.util.ProjectConstants;
import co.com.tallercloud.backlog.infraestructure.driven.rest.ProjecTaskClient;
import co.com.tallercloud.backlog.infraestructure.driven.rest.ProjectClient;
import co.com.tallercloud.backlog.infraestructure.services.gateway.BackLogService;
import co.com.tallercloud.backlog.infraestructure.services.repository.BacklogRepository;
import co.com.tallercloud.backlog.model.exception.BacklogGenericExceptionNotFound;
import co.com.tallercloud.backlog.model.exception.ProjectExceptionBadRequest;
import co.com.tallercloud.backlog.model.exception.StatusResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@AllArgsConstructor
public class BacklogServiceImpl implements BackLogService {

    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private final ProjecTaskClient projecTaskClient;
      @Autowired
    private final ProjectClient projectClient;

    @Override
    public Backlog saveBacklog(String projectIdentifier){
        Backlog backlog = new Backlog();
        backlog.setProjectIdentifier(projectIdentifier);
        backlog = backlogRepository.save(backlog);
        try {
            if(!StringUtils.isEmpty(backlog)){
                return  backlog;
            }else{
                log.info("Se ha presentado un error guardando en la base de datos.");
                return  errordataBase();
            }
        }catch (DataIntegrityViolationException e){
            log.info("Se ha presentado un error guardando en la base de datos. Error: "+e);
            return  errordataBase();
        }
    }
    @Override
    public Backlog getBackLog(String  projectIdentifier) {
        Backlog backlog = new Backlog();
        executeRestApiTasksProject(projectIdentifier,backlog);
        executeRestApiProject(projectIdentifier,backlog);

        if(StringUtils.isEmpty(backlog.getProject()) && StringUtils.isEmpty(backlog.getProjectTask())){
            structureError();
        }

        return backlog;
    }
    private void executeRestApiTasksProject(String projectIdentifier, Backlog backlog) {
        try {
            ResponseEntity<ProjectTaskRS> responseEntity =
                    projecTaskClient.getprojectTask(projectIdentifier);

            backlog.setProjectIdentifier(projectIdentifier);
            if (!StringUtils.isEmpty(responseEntity)) {
                log.info("Código de respuesta al consultar las tareas del proyecto: " + projectIdentifier + " " +
                        "Code:" + responseEntity.getStatusCode());
                if ((responseEntity.getStatusCode().is2xxSuccessful() &&
                        !StringUtils.isEmpty(responseEntity.getBody().getProjectTaskRSAttributes().isEmpty()))
                        && !responseEntity.getBody().getProjectTaskRSAttributes().isEmpty()) {

                    backlog.setProjectTask(responseEntity.getBody().getProjectTaskRSAttributes().get(0)
                            .getProjectTaskRSAttributesData().getTaskList());
                } else {
                    log.info("Error al obtener los datos retornados por el webclient para el proyecto:" + projectIdentifier,
                            "Error:" + responseEntity);
                }
            } else {
                structureError();
            }
        } catch(RuntimeException e){
                log.info("Error al consultar Tareas de un proyecto. Identificador: " + projectIdentifier,
                        "Error:" + e);
        }

    }

    private void executeRestApiProject(String projectIdentifier, Backlog backlog){
        try {

            ResponseEntity<ProjectRS> responseEntityProject = projectClient.getProject(projectIdentifier);
            if (!StringUtils.isEmpty(responseEntityProject)) {
                log.info("Código de respuesta al consultar el proyecto: " + projectIdentifier + " " +
                        "Code:" + responseEntityProject.getStatusCode());
                if ((responseEntityProject.getStatusCode().is2xxSuccessful() &&
                        !StringUtils.isEmpty(responseEntityProject.getBody().getProjectRSAttributes().isEmpty()))
                        && !responseEntityProject.getBody().getProjectRSAttributes().isEmpty()) {
                    backlog.setProject(responseEntityProject.getBody().getProjectRSAttributes().get(0)
                            .getProjectRSAttributesData().getProject());

                }
            }else {
                structureError();
            }
        }catch (RuntimeException e){
            log.info("Se ha presentado un error obteniendo la respuesta al consultar las tareas de un proyecto. " +
                    "Error: "+e);
        }
    }

    private Backlog structureError(){
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setErrorCode(ProjectConstants.CODE404);
        statusResponse.setErrorMessage(ProjectConstants.ERRORDESC);
        statusResponse.setErrorDesc(ProjectConstants.ERRORINQUIRY);
        statusResponse.setModule(ProjectConstants.MODULE);
        throw new ProjectExceptionBadRequest(statusResponse);
    }

    private Backlog errordataBase(){
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setErrorCode(ProjectConstants.CODE500);
        statusResponse.setErrorMessage(ProjectConstants.ERRORSAVE);
        statusResponse.setErrorDesc(ProjectConstants.ERRORSAVE);
        statusResponse.setModule(ProjectConstants.MODULE);
        throw new BacklogGenericExceptionNotFound(statusResponse);
    }


}
