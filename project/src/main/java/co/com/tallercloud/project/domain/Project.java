package co.com.tallercloud.project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "tbl_backlogs")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_identifier")
    @NotBlank(message = "El identificador del proyecto no puede ir vacío")
    private String projectIdentifier;

    @Column(name = "project_name")
    private String projectName;

    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;


}
