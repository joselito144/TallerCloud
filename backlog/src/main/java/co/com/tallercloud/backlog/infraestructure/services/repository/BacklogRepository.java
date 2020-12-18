package co.com.tallercloud.backlog.infraestructure.services.repository;


import co.com.tallercloud.backlog.domain.entities.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog,Long> {


}
