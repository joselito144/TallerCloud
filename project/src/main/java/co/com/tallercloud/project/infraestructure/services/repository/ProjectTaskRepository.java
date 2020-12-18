package co.com.tallercloud.project.infraestructure.services.repository;


import co.com.tallercloud.project.domain.entities.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask,Long> {

    List<ProjectTask>  findByProjectIdentifier(String projectIdentifier);

}
