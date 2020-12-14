package co.com.tallercloud.backlog.client;

import co.com.tallercloud.backlog.model.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-project", fallback = ProjectClientHystrixFallback.class)
public interface ProjectClient {

    @GetMapping(value = "/project/{projectIdenfier}")
    ResponseEntity<Project> getProject(@PathVariable("projectIdentifier") String projectIdentifier);

}
