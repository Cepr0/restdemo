package restsdemo.example9;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 */
public interface WorkerRepo extends JpaRepository<Worker, Long> {
    List<Worker> findByPositions(Position position);

    @Query("select distinct w from Worker w join fetch w.positions where w.name = ?1")
    List<Worker> findByNameQuery(String name);

    @EntityGraph(attributePaths = "positions")
    List<Worker> findDistinctByName(String name);

    @Query("select w from Worker w join fetch w.positions where w.name = ?1")
    Worker findWorkerByName(String name);
}
