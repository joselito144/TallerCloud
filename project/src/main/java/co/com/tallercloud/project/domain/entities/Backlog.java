package co.com.tallercloud.project.domain.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="tbl_backlog")
public class Backlog {

    @Id
    @PrimaryKeyJoinColumn
    @Column(name = "id")
    Long id;

    @Column(name="projectIdentifier")
    String projectIdentifier;

    @Transient
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_project")
    private Project project;

    @Transient
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="id_project_task")
    private List<ProjectTask> projectTask;

}
