package co.com.tallercloud.backlog.infraestructure.controller.java.models.project.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;


@Setter
@Getter
public class ProjectRSAttributes {

    @Valid
    @JsonProperty("attributes")
    private ProjectRSAttributesData projectRSAttributesData;
}
