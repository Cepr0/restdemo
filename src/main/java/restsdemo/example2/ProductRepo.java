package restsdemo.example2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Cepro, 2016-12-29
 */
@RepositoryRestResource
public interface ProductRepo extends JpaRepository<Product, Long> {
}
