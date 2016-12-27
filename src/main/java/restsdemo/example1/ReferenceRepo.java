package restsdemo.example1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Cepro, 2016-12-24
 */
@RepositoryRestResource
public interface ReferenceRepo extends JpaRepository<Reference, Long> {
}
