package co.com.tallercloud.backlog.controller;

import co.com.tallercloud.backlog.domain.Backlog;
import co.com.tallercloud.backlog.model.ErrorMessage;
import co.com.tallercloud.backlog.services.BacklogService;
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
@RequestMapping(value = "/backlog")
public class BacklogController {

    private final BacklogService backlogService;

    public BacklogController(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @GetMapping
    public ResponseEntity<List<Backlog>> listCustomers() {
        List<Backlog> backlogs = backlogService.getCustormerAll();
        if (backlogs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(backlogs);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Backlog> getCustomer(@PathVariable("id") Long id) {
        log.info("Backlog with id {}", id);
        Backlog customer = backlogService.getCustomer(id);
        if (customer == null) {
            log.info("Customer with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
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
