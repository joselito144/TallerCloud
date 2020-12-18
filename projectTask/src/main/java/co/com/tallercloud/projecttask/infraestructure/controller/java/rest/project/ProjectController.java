package co.com.tallercloud.projecttask.infraestructure.controller.java.rest.project;

import co.com.tallercloud.projecttask.infraestructure.controller.java.models.project.ProjectRQ;
import co.com.tallercloud.projecttask.domain.entities.Project;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/project")
public class ProjectController {


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createProject(@Valid @RequestBody ProjectRQ projectRQ){

        Project project = new Project();
        BeanUtils.copyProperties(projectRQ.getProjectRQAttributes(),project);
        return ResponseEntity.created(URI.create("project/create")).body(true);
    }



}
