package co.com.tallercloud.backlog.infraestructure.driven.rest;

import co.com.tallercloud.backlog.infraestructure.controller.java.models.projectTask.output.ProjectTaskRS;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "projecttask-service")
@RequestMapping("/task")
public interface ProjecTaskClient {


    @GetMapping("/project/{projectIdentifier}")
    ResponseEntity<ProjectTaskRS> getprojectTask(@PathVariable("projectIdentifier") String projectIdentifier);

}
