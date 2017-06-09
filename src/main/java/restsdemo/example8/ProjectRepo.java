package restsdemo.example8;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface ProjectRepo extends JpaRepository<Project, Long> {
}
