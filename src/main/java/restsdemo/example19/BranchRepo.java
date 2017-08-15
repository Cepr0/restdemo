package restsdemo.example19;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Cepro
 *         2017-08-15
 */
@RepositoryRestResource
public interface BranchRepo extends JpaRepository<Branch, Long> {
}
