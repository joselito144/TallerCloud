package co.com.tallercloud.project.infraestructure.services.repository;


import co.com.tallercloud.project.domain.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByProjectIdentifier(String projectIdentifier);

}
