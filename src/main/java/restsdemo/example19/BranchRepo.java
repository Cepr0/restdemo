package restsdemo.example19;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Example for https://stackoverflow.com/a/46373345/5380322
 * @author Cepro
 *         2017-08-15
 */
@NoRepositoryBean
public interface BranchRepo extends JpaRepository<Branch, Long> {

	List<Branch> getBranches();
}
