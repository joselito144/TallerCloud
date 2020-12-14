package co.com.tallercloud.project.controller;

import co.com.tallercloud.project.domain.Project;
import co.com.tallercloud.project.model.ErrorMessage;
import co.com.tallercloud.project.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> listCustomers() {
        List<Project> customers = projectService.getProyectsAll();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/{projectIdenfier}")
    public ResponseEntity<Project> getCustomer(@PathVariable("projectIdenfier") String identifier) {
        log.info("Project with identifier {}", identifier);
        Project project = projectService.getProjectByIdenfier(identifier);
        if (project == null) {
            log.info("Project with id {} not found", identifier);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(project);
    }



    private String formatMessage(BindingResult result) {
        List<Map<String, String>> messages = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(messages).build();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


}
