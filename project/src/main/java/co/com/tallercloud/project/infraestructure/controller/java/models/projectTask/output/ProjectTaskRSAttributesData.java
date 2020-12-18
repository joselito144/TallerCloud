package co.com.tallercloud.project.infraestructure.controller.java.models.projectTask.output;

import co.com.tallercloud.project.domain.model.ProjectTaskDao;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectTaskRSAttributesData {

    @JsonProperty("projectIdentifier")
    private String projectId;
    List<ProjectTaskDao> taskList = null;
    private String uri = null;
    private Double totalHours=null;

}
