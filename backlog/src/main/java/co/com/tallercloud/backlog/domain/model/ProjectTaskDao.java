package co.com.tallercloud.backlog.domain.model;

import co.com.tallercloud.backlog.domain.entities.Backlog;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectTaskDao {


    private Long id;
    private String name;
    private String summary;
    private String description;
    private String acceptanceCriteria;
    private String status;
    private int priority;
    private Double hours;
    private Date startDate;
    private Date endDate;
    private String projectIdentifier;
    private Backlog backlog;
}
