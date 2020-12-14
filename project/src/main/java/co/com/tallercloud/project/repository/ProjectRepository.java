package co.com.tallercloud.project.repository;

import co.com.tallercloud.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<Project> findById(Long id);

    Project findByProjectIdentifier(String projectIdentifier);

}
