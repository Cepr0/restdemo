package restsdemo.example4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 */
@RepositoryRestResource//(path = "criterions", collectionResourceRel = "criterions", itemResourceRel = "criterion")
public interface CriterionRepository extends JpaRepository<NameCriterion, Long> {
}
