package restsdemo.example9;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface WorkerRepo extends JpaRepository<Worker, Long> {
    List<Worker> findByPositions(Position position);
}
