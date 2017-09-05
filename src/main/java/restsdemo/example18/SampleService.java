package restsdemo.example18;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Cepro, 2017-09-04
 */
@RequiredArgsConstructor
@Service
public class SampleService {

	private final BatchSave batchSave;

	// @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize = 20;

	private List<Sample> savedEntities;

	public Collection<Sample> bulkSave(List<Sample> samples) {
		int size = samples.size();
		savedEntities = new ArrayList<>(size);

		try {
			for (int i = 0; i < size; i += batchSize) {
				int toIndex = i + (((i + batchSize) < size) ? batchSize : size - i);
				savedEntities.addAll(batchSave.process(samples.subList(i, toIndex)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savedEntities;
	}

}
