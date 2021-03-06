package co.com.tallercloud.projecttask.infraestructure.controller.java.rest.backlog;

import co.com.tallercloud.projecttask.infraestructure.controller.java.models.backlog.BacklogRQ;
import co.com.tallercloud.projecttask.domain.entities.Project;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/backlog")
public class BacklogController {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createBacklog(@Valid @RequestBody BacklogRQ backlogRQ){

        Project project = new Project();
        BeanUtils.copyProperties(backlogRQ.getBackLogRQAttributes(),project);
        return ResponseEntity.created(URI.create("project/create")).body(true);
    }



}
