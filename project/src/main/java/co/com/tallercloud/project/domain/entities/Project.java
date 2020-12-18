package co.com.tallercloud.project.domain.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "tbl_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    @Column(name = "id")
    private Long id;

    @Column(name = "project_name",unique = true)
    private String projectName;
    @Column(name = "project_identifier",updatable = false,unique = true)
    private String projectIdentifier;
    @NotEmpty
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;





}
