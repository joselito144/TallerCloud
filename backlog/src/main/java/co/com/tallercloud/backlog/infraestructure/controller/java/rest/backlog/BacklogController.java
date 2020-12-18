package co.com.tallercloud.backlog.infraestructure.controller.java.rest.backlog;

import co.com.tallercloud.backlog.domain.entities.Backlog;
import co.com.tallercloud.backlog.infraestructure.controller.java.models.backlog.BacklogRQ;
import co.com.tallercloud.backlog.domain.entities.Project;
import co.com.tallercloud.backlog.infraestructure.controller.java.models.backlog.output.BackLogRSAttributes;
import co.com.tallercloud.backlog.infraestructure.controller.java.models.backlog.output.BacklogRS;
import co.com.tallercloud.backlog.infraestructure.controller.java.models.backlog.output.BacklogRSAttributesData;
import co.com.tallercloud.backlog.infraestructure.services.gateway.BackLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/backlog")
public class BacklogController {

    private final BackLogService backLogService;

    public BacklogController(BackLogService backLogService){
        this.backLogService = backLogService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createBacklog(@Valid @RequestBody BacklogRQ backlogRQ){

        Project project = new Project();
        BeanUtils.copyProperties(backlogRQ.getBackLogRQAttributes(),project);
        return ResponseEntity.created(URI.create("project/create")).body(true);

    }

    @GetMapping("/{projectIdentifier}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getBacklog(@PathVariable("projectIdentifier") String projectIdentifier){

        Backlog backlog = backLogService.getBackLog(projectIdentifier);
        return  ResponseEntity.ok().body(structureResponse(backlog));

    }


    private BacklogRS structureResponse(Backlog backlog){
        BacklogRS backlogRS = new BacklogRS();
        BackLogRSAttributes backLogRSAttributes = new BackLogRSAttributes();
        BacklogRSAttributesData data = new BacklogRSAttributesData();
        backLogRSAttributes.setDate(new Date().toString());
        data.setBacklog(backlog);
        backLogRSAttributes.setBacklogRSAttributesData(data);
        List<BackLogRSAttributes> listResponse= new ArrayList<>();
        listResponse.add(backLogRSAttributes);
        backlogRS.setBackLogRSAttributes(listResponse);

        return backlogRS;
    }




}
