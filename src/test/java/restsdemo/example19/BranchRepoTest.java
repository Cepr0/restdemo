package restsdemo.example19;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import restsdemo.BaseTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sergei Poznanski, 2017-09-23
 */
@ActiveProfiles("two")
public class BranchRepoTest extends BaseTest {
	
	@Autowired
	private BranchRepo branchRepo;
	
	@Test
	public void getBranchesTest() throws Exception {
		// given
		Branch root = branchRepo.save(new Branch("root", null));
		Branch branch1 = branchRepo.save(new Branch("branch1", root));
		Branch branch2 = branchRepo.save(new Branch("branch2", branch1));
		
		// when
		List<Branch> branches = branchRepo.getBranches();
		
		// then
		assertThat(branches).hasSize(1);
	}
}