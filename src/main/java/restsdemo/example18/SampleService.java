package restsdemo.example18;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Cepro, 2017-09-04
 */
@Service
public class SampleService {

	@PersistenceContext
	private EntityManager em;

	// @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize = 20;

	private List<Sample> savedEntities;

	public Collection<Sample> bulkSave(List<Sample> samples) {
		int size = samples.size();
		savedEntities = new ArrayList<>(size);

		try {
			for (int i = 0; i < size; i += batchSize) {
				int toIndex = i + (((i + batchSize) < size) ? batchSize : size - i) ;
				processChunk(samples.subList(i, toIndex));
				em.flush();
				em.clear();
			}
		} catch (Exception ignored) {
		}
		return savedEntities;
	}

	@Transactional
	protected void processChunk(List<Sample> batch) {
		if (savedEntities.size() > 20) throw new RuntimeException();
		for (Sample t : batch) {
			Sample result;
			if (t.getId() == null) {
				em.persist(t);
				result = t;
			} else {
				result = em.merge(t);
			}
			savedEntities.add(result);
		}
	}
}
