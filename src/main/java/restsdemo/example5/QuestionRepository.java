package restsdemo.example5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 */
@RepositoryRestResource(exported = false)
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
