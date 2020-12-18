package co.com.tallercloud.backlog.infraestructure.controller.java.models.project.output;

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
public class ProjectRS {

    @JsonProperty("data")
    @NotNull
    @Valid
    private List<ProjectRSAttributes> projectRSAttributes;


}
