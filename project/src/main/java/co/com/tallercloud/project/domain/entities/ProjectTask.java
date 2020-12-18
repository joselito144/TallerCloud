package co.com.tallercloud.project.domain.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "tbl_project_task")
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project_task")
    private Long id;
    @NotEmpty
    @Column(name = "name")
    private String name;
    @Column(name = "summary")
    private String summary;
    @NotEmpty
    @Column(name = "description")
    private String description;
    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;
    @Column(name = "status")
    private String status;
    @Column(name = "priority")
    private int priority;
    @Column(name = "hours")
    private Double hours;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @NotEmpty
    @Column(name = "project_identifier")
    private String projectIdentifier;




}
