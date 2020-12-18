package co.com.tallercloud.backlog.infraestructure.controller.java.models.project.output;

import co.com.tallercloud.backlog.domain.entities.Project;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter
public class ProjectRSAttributesData {

    private Project project;

}
