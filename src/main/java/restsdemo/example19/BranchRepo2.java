package restsdemo.example19;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author Sergei Poznanski, 2017-09-23
 */
@Profile("two")
@RepositoryRestResource
public interface BranchRepo2 extends BranchRepo {
	
	@Override
	@Query("select b from Branch b where b.id = 1")
	List<Branch> getBranches();
}
