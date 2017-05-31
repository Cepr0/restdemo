package restsdemo.example4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 */
@RepositoryRestResource(path = "criteria", collectionResourceRel = "criteria", itemResourceRel = "criterion")
public interface CriterionRepository extends JpaRepository<Criterion, Long> {
}
