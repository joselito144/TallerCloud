package co.com.tallercloud.backlog.domain.entities;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    @Column(name = "id")
    Long id;

    @Column(unique = true,name="projectIdentifier")
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
