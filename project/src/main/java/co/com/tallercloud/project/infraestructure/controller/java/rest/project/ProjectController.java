package co.com.tallercloud.project.infraestructure.controller.java.rest.project;

import co.com.tallercloud.project.domain.model.ProjectDao;
import co.com.tallercloud.project.infraestructure.controller.java.models.project.input.ProjectRQ;
import co.com.tallercloud.project.infraestructure.controller.java.models.project.outpu.ProjectRS;
import co.com.tallercloud.project.infraestructure.controller.java.models.project.outpu.ProjectRSAttributes;
import co.com.tallercloud.project.infraestructure.controller.java.models.project.outpu.ProjectRSAttributesData;
import co.com.tallercloud.project.infraestructure.services.gateway.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createProject(@Valid @RequestBody ProjectRQ projectRQ){

        ProjectDao project = new ProjectDao();
        BeanUtils.copyProperties(projectRQ.getProjectRQAttributes().get(0).getProjectRQAttributesData(),project);

        return ResponseEntity.created(URI.create("project/create")).
                body(structureResponse(projectService.createProject(project),"POST", new ArrayList<>()));
    }

    @GetMapping("/{projectidentifier}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getProject(@PathVariable("projectidentifier") String projectidentifier){

        return ResponseEntity.ok().body(structureResponse(projectService.getProject(projectidentifier),"GET",
                new ArrayList<>()));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllProject(){

        return ResponseEntity.ok().body(structureResponse(new ProjectDao(),"GET",
                projectService.getAllProject()));
    }

    private ProjectRS structureResponse(ProjectDao projectDao, String operation,List<ProjectDao> projectDaoList){

        ProjectRS rs = new ProjectRS();
        ProjectRSAttributes attributes = new ProjectRSAttributes();
        ProjectRSAttributesData data = new ProjectRSAttributesData();
        if(!projectDaoList.isEmpty()){
            data.setProjects(projectDaoList);
        }else{
            data.setProjectDao(projectDao);
        }
        if("POST".equals(operation)){
            data.setUri("project/create");
        }
        attributes.setProjectRSAttributesData(data);
        List<ProjectRSAttributes> listAttributes = new ArrayList<>();
        listAttributes.add(attributes);
        rs.setProjectRSAttributes(listAttributes);
        return rs;

    }
}
