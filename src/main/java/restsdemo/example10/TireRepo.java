package restsdemo.example10;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Cepro
 * @since 2017-07-07
 */
public interface TireRepo extends JpaRepository<Tire, Long> {
}
