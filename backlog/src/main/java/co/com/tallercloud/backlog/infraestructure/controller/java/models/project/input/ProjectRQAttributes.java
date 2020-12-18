package co.com.tallercloud.backlog.infraestructure.controller.java.models.project.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;


@Setter
@Getter
public class ProjectRQAttributes {

    @Valid
    @JsonProperty("attributes")
    private ProjectRQAttributesData projectRQAttributesData;
}
