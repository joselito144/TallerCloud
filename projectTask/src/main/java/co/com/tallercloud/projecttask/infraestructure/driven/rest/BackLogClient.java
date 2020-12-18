package co.com.tallercloud.projecttask.infraestructure.driven.rest;

import co.com.tallercloud.projecttask.domain.entities.Backlog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "backlog-service")
@RequestMapping("/backlog")
public interface BackLogClient {


    @GetMapping("/{projectIdentifier}")
    ResponseEntity<Backlog> getBacklog(@PathVariable("projectIdentifier") String projectIdentifier);

}
