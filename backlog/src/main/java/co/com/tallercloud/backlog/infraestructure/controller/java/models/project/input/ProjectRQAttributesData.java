package co.com.tallercloud.backlog.infraestructure.controller.java.models.project.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Validated
@Setter
@Getter
public class ProjectRQAttributesData {

    @Valid
    @NotNull
    @NotBlank
    @JsonProperty("projectName")
    private String projectName;
    @Length(min = 5,max = 7)
    @JsonProperty("projectIdentifier")
    private String projectIdentifier;
    @JsonProperty("description")
    private String descripcion;
    @JsonProperty("starDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;

}
