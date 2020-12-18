package co.com.tallercloud.project.domain.model;


import co.com.tallercloud.project.domain.entities.Project;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDao extends Project {


}
