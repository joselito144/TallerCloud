package co.com.tallercloud.backlog.infraestructure.controller.java.models.projectTask.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Setter
@Getter
public class ProjectTaskRQAttributes {

    @Valid
    @JsonProperty("attributes")
    private ProjectTaskRQAttributesData projectTaskRQAttributesData;

}
