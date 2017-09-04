package restsdemo.example15;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * @author Cepro
 * @since 2017-07-23
 */
@RepositoryRestResource
public interface MasterRepo extends JpaRepository<Master, Long> {
    
    @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
    Optional<MasterDto> findById(Long id);

    Optional<Master> findByName(String name);

    List<MasterDto> findBy();
}
