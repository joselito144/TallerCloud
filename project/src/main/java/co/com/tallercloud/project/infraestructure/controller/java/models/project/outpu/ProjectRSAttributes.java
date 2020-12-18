package co.com.tallercloud.project.infraestructure.controller.java.models.project.outpu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectRSAttributes {

    @Valid
    @JsonProperty("attributes")
    private ProjectRSAttributesData projectRSAttributesData;
}
