package co.com.tallercloud.backlog.client;

import co.com.tallercloud.backlog.model.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectClientHystrixFallback implements ProjectClient{
    @Override
    public ResponseEntity<Project> getProject(String id) {
        Project project = Project.builder().build();
        return ResponseEntity.ok(project);
    }
}
