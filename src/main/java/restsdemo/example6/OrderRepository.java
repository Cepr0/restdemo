package restsdemo.example6;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface OrderRepository extends JpaRepository<Orderr, Long> {
}
