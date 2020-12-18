package co.com.tallercloud.projecttask.infraestructure.controller.java.models.backlog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter
public class BacklogRQAttributesData {

    @NotNull
    @JsonProperty("projectIdentifier")
    private String projectIdentifier;

}
