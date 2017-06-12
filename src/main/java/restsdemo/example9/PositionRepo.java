package restsdemo.example9;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface PositionRepo extends JpaRepository<Position, Long> {
}
