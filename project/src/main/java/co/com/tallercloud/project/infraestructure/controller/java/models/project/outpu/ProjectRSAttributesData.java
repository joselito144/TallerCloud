package co.com.tallercloud.project.infraestructure.controller.java.models.project.outpu;

import co.com.tallercloud.project.domain.model.ProjectDao;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectRSAttributesData {

    @JsonProperty("project")
    private ProjectDao projectDao;
    @JsonProperty("projects")
    private List<ProjectDao> projects;
    @JsonProperty("uri")
    private String uri;

}
