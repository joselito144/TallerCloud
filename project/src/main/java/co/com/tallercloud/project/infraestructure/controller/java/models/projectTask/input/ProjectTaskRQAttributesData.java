package co.com.tallercloud.project.infraestructure.controller.java.models.projectTask.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.sql.Date;

@Validated
@Setter
@Getter
public class ProjectTaskRQAttributesData {

    @NotNull
    @NotBlank
    @JsonProperty("name")
    private String name;
    @NotNull
    @NotBlank
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("description")
    private String description;
    @JsonProperty("acceptanceCriteria")
    private String acceptanceCriteria;
    @NotBlank
    @NotNull
    @Valid
    @Pattern(regexp = "(Not started|In progress|completed|deleted)")
    @JsonProperty("status")
    private String status;
    @Valid
    @Min(1)
    @Max(5)
    @JsonProperty("priority")
    private int priority;
    @Valid
    @Min(1)
    @Max(8)
    //Pendiente validar números positivos
    @JsonProperty("hours")
    @Positive
    private Double hours;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;
    @JsonProperty("project_identifier")
    private String projectIdentifier;

}
