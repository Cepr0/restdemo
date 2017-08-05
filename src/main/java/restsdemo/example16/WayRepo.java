package restsdemo.example16;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Cepro
 * @since 2017-08-03
 */
public interface WayRepo extends JpaRepository<Way, Long> {
}
