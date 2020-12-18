package co.com.tallercloud.project.infraestructure.controller.java.models.project.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Setter
@Getter
public class ProjectRQ {

    @JsonProperty("data")
    @NotNull
    @Valid
    private List<ProjectRQAttributes> projectRQAttributes;


}
