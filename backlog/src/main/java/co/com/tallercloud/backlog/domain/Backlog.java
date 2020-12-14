package co.com.tallercloud.backlog.domain;

import co.com.tallercloud.backlog.model.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_backlogs")
@Getter
@Setter
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_identifier")
    @NotBlank(message = "El identificador del proyecto no puede ir vacío")
    private String projectIdentifier;

    @Transient
    private Project project;


}
