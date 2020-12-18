package co.com.tallercloud.project.infraestructure.services;

import co.com.tallercloud.project.domain.entities.Project;
import co.com.tallercloud.project.domain.model.ProjectDao;
import co.com.tallercloud.project.domain.model.ProjectExceptionBadRequest;
import co.com.tallercloud.project.domain.model.ProjectExceptionNotFound;
import co.com.tallercloud.project.domain.model.StatusResponse;
import co.com.tallercloud.project.infraestructure.controller.java.models.util.ProjectTaskConstants;
import co.com.tallercloud.project.infraestructure.services.gateway.ProjectService;
import co.com.tallercloud.project.infraestructure.services.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private final ProjectRepository projectRepository;

    @Override
    public ProjectDao createProject(ProjectDao project) {
        try {
            Project projectDB = new Project();
            BeanUtils.copyProperties(project,projectDB);
            projectDB= projectRepository.save(projectDB);
            BeanUtils.copyProperties(projectDB,project);
            return  project;
        }catch (DataIntegrityViolationException exception){
             return structureError();
        }

    }

    @Override
    public ProjectDao getProject(String projectIdentifier){

        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);

        if(!StringUtils.isEmpty(project)){
            ProjectDao projectDao = new ProjectDao();
            BeanUtils.copyProperties(project,projectDao);
            return projectDao;
        }else{
            return responseError();
        }
    }

    @Override
    public List<ProjectDao> getAllProject() {
        List<Project> list = projectRepository.findAll();
        if (!list.isEmpty()) {
            List<ProjectDao> projectDaoList = new ArrayList<>();
            list.forEach(response -> {
                ProjectDao projectDao = new ProjectDao();
                BeanUtils.copyProperties(response, projectDao);
                projectDaoList.add(projectDao);
            });

            return projectDaoList;
        } else {
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setErrorCode("404");
            statusResponse.setErrorMessage("No se han encontrado proyectos para mostrar");
            statusResponse.setErrorDesc("No se han encontrado proyectos para mostrar");
            statusResponse.setModule(ProjectTaskConstants.MODULE);
            throw new ProjectExceptionNotFound(statusResponse);

        }
    }

    private ProjectDao structureError(){
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setErrorCode(ProjectTaskConstants.CODE400);
        statusResponse.setErrorMessage(ProjectTaskConstants.ERRORDESC);
        statusResponse.setErrorDesc(ProjectTaskConstants.ERRORDUPLICATED);
        statusResponse.setModule(ProjectTaskConstants.MODULE);
        throw new ProjectExceptionBadRequest(statusResponse);
    }

    private ProjectDao responseError(){
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setErrorCode("404");
        statusResponse.setErrorMessage("Se ha presentando un error consultando en la base de datos");
        statusResponse.setErrorDesc("No se han encontrado datos para el projecto");
        statusResponse.setModule("projectask");
        throw new ProjectExceptionNotFound(statusResponse);
    }



}
