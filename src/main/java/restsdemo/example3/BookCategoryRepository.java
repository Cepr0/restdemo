package restsdemo.example3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cepro, 2016-12-28
 */
@RepositoryRestResource
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
	
	<T extends OneField> List<T> getDistinctBy(Class<T> type);

	default <T extends OneField> List<?> getFieldValues(Class<T> type) {
		return getDistinctBy(type)
				.stream()
				.map(T::getValue)
				.collect(Collectors.toList());
	}
}