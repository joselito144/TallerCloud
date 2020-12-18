package co.com.tallercloud.backlog.domain.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Table(name = "tbl_project")
public class Project {

    @Id
    @PrimaryKeyJoinColumn
    @Column(name = "id_project_task")
    private Long id;
    //Validar como hacer único
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_identifier")
    private String projectIdentifier;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Transient
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Backlog backlog;




}
