package restsdemo.example3;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restsdemo.BaseTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Cepro, 2017-09-29
 */
public class BookCategoryRepositoryTest extends BaseTest {
	
	@Autowired
	private BookCategoryRepository repo;
	
	@Test
	public void findNameByTest() throws Exception {
		
		List<?> names = repo.getFieldValues(NameField.class);
		assertThat(names).isNotNull();
	}
	
	public interface NameField extends OneField<String> {
		
		@Override
		default String getValue() {
			return getName();
		}

		String getName();
	}
}