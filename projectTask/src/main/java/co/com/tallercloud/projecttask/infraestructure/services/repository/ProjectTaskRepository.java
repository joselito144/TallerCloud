package co.com.tallercloud.projecttask.infraestructure.services.repository;


import co.com.tallercloud.projecttask.domain.entities.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask,Long> {

    List<ProjectTask>  findByProjectIdentifier(String projectIdentifier);

}
