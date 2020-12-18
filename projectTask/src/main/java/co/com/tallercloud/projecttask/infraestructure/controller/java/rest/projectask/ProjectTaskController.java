package co.com.tallercloud.projecttask.infraestructure.controller.java.rest.projectask;


import co.com.tallercloud.projecttask.domain.model.ProjectTaskDao;
import co.com.tallercloud.projecttask.infraestructure.controller.java.models.projectTask.input.ProjectTaskRQ;
import co.com.tallercloud.projecttask.infraestructure.controller.java.models.projectTask.output.ProjectTaskRS;
import co.com.tallercloud.projecttask.infraestructure.controller.java.models.projectTask.output.ProjectTaskRSAttributes;
import co.com.tallercloud.projecttask.infraestructure.controller.java.models.projectTask.output.ProjectTaskRSAttributesData;
import co.com.tallercloud.projecttask.infraestructure.services.gateway.ProjectTaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
//@FeignClient(serviceId = "projectask", url = "localhost:8091")
public class ProjectTaskController {

    private final ProjectTaskService projectTaskService;

    public ProjectTaskController(ProjectTaskService projectTaskService){
        this.projectTaskService = projectTaskService;
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createProject(@Valid @RequestBody ProjectTaskRQ projectTaskRQ){

        ProjectTaskDao task = new ProjectTaskDao();
        BeanUtils.copyProperties(projectTaskRQ.getProjectTaskRQAttributes().get(0).getProjectTaskRQAttributesData(),task);
        ProjectTaskDao projectTask = projectTaskService.createTask(task);
        List<ProjectTaskDao> projectTaskList = new ArrayList<>();
        projectTaskList.add(projectTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(structureRequest(projectTaskList,null, "POST"));
    }





    @GetMapping("/project/{projectIdentifier}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getProject(@Valid @PathVariable("projectIdentifier") String projectIdentifier){

        List<ProjectTaskDao> task = projectTaskService.getByProjectIdentifier(projectIdentifier);
        return ResponseEntity.ok().body(structureRequest(task,null,"GET"));


    }

    @GetMapping("/hours/project/{projectIdentifier}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> getTotalHoursByProject(@Valid @PathVariable("projectIdentifier") String projectIdentifier){

        Double totalHours = projectTaskService.getTotalHoursByProjectIdentifier(projectIdentifier);
        return ResponseEntity.ok().body(structureRequest(new ArrayList<>(),totalHours,"GET"));
    }

    @GetMapping("/hours/project/{projectIdentifier}/{status}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> getTotalHoursByProjectStatus(@Valid @PathVariable("projectIdentifier")
                                                                           String projectIdentifier,
                                                               @PathVariable("status") String status){

        Double totalHours = projectTaskService.getTotalHoursByProjectIdentifierStatus(projectIdentifier,status);
        return ResponseEntity.ok().body(structureRequest(new ArrayList<>(),totalHours,"GET"));
    }

    @DeleteMapping("/{idtask}/{projectIdentifier}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> deleteTask(@Valid @PathVariable("idtask") Long id,
                                             @PathVariable("projectIdentifier") String projectIdentifier){

        ProjectTaskDao projectTask = projectTaskService.deleteStatusTask(projectIdentifier,id);
        List<ProjectTaskDao> projectTaskList = new ArrayList<>();
        projectTaskList.add(projectTask);
        return ResponseEntity.ok().body(structureRequest(projectTaskList,null,"DELETE"));
    }

    private ProjectTaskRS structureRequest(List<ProjectTaskDao> listProjectTask, Double hour, String method){
        ProjectTaskRS projectTaskRS = new ProjectTaskRS();
        ProjectTaskRSAttributes projectTaskRSAttributes = new ProjectTaskRSAttributes();
        ProjectTaskRSAttributesData data = new ProjectTaskRSAttributesData();
        projectTaskRSAttributes.setDate(new Date().toString());
        if(!listProjectTask.isEmpty()){
            data.setProjectId(listProjectTask.get(0).getProjectIdentifier());
        }
        if(!listProjectTask.isEmpty()){
            if("POST".equals(method)){
                data.setUri("/task/create");
            }
            data.setTaskList(listProjectTask);
        }
        if(!StringUtils.isEmpty(hour)){
            data.setTotalHours(hour);
        }
        projectTaskRSAttributes.setProjectTaskRSAttributesData(data);
        List<ProjectTaskRSAttributes> listResponse= new ArrayList<>();
        listResponse.add(projectTaskRSAttributes);
        projectTaskRS.setProjectTaskRSAttributes(listResponse);

        return projectTaskRS;
    }






}
