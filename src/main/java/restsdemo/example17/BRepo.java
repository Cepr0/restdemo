package restsdemo.example17;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Cepro
 * @since 2017-08-07
 */
public interface BRepo extends CrudRepository<B, Long> {
    
    // @EntityGraph(attributePaths = {"a"})
    BProjection findById(Long id);
}
