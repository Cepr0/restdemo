package restsdemo.example9;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    List<Worker> findDistinctByPositionsIn(List<Position> positions);

    @Query("select w from Worker w join w.positions p where p in (?1) group by w having count(p) >= (select count(p2) from Position p2 where p2 in (?1))")
    List<Worker> findIfSubsetExists(@Param("positions") List<Position> positions);

    List<Worker.WithPositions> findDistinctBy();
}
