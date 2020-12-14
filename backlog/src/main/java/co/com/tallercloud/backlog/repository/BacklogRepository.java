package co.com.tallercloud.backlog.repository;

import co.com.tallercloud.backlog.domain.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog,Long> {

    Optional<Backlog> findById(Long id);

}
