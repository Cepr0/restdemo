package restsdemo.example6;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 */
@RepositoryRestResource(exported = false)
public interface OrderRepository extends JpaRepository<Orderr, Long> {
}
