package co.com.tallercloud.backlog.infraestructure.driven.rest;

import co.com.tallercloud.backlog.infraestructure.controller.java.models.project.output.ProjectRS;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "project-service")
@RequestMapping("/project")
public interface ProjectClient {


    @GetMapping("/{projectIdentifier}")
    ResponseEntity<ProjectRS> getProject(@PathVariable("projectIdentifier") String projectIdentifier);

}
