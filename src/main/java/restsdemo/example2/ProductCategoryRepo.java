package restsdemo.example2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Cepro
 *         2017-06-21
 */
@RepositoryRestResource
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
}
