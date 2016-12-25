package restsdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import restsdemo.domain.entity.Child;

/**
 * @author Cepro, 2016-12-25
 */
@RepositoryRestResource
public interface ChildRepo extends JpaRepository<Child, Long> {
}
