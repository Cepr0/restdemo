package restsdemo.example2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Cepro, 2016-12-27
 */
@RepositoryRestResource
public interface OrderRepo extends JpaRepository<Order, Long> {
}
