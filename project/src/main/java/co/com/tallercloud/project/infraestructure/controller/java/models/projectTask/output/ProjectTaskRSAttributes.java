package co.com.tallercloud.project.infraestructure.controller.java.models.projectTask.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter

public class ProjectTaskRSAttributes {

    @JsonProperty("date")
    private String date;
    @JsonProperty("attributes")
    private ProjectTaskRSAttributesData projectTaskRSAttributesData;



}
