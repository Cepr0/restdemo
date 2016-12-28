package restsdemo.example3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Cepro, 2016-12-28
 */
@RepositoryRestResource
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
}