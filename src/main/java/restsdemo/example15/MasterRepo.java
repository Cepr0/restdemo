package restsdemo.example15;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Cepro
 * @since 2017-07-23
 */
@RepositoryRestResource
public interface MasterRepo extends JpaRepository<Master, Long> {
}
