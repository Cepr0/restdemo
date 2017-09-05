package restsdemo.example18;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import restsdemo.BaseTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * @author Cepro, 2017-09-04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BulkSaveTest {

	@Autowired
	private SampleService service;

	@Test
	public void bulkSave() throws Exception {

		int size = 59;
		List<Sample> samples = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			samples.add(new Sample());
		}

		Collection<Sample> result = service.bulkSave(samples);
		assertThat(result).hasSize(59);
	}
}