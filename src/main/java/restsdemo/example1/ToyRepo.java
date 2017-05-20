package restsdemo.example1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 */
@RepositoryRestResource
public interface ToyRepo extends JpaRepository<Toy, Long> {
}
