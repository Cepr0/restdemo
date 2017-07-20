package restsdemo.example14;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restsdemo.BaseTest;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Cepro
 *         2017-07-19
 */
public class BaseEntityRepoTest extends BaseTest {

    @Autowired
    private BaseEntityRepo repo;

    @Before
    public void setUp() throws Exception {

        repo.save(asList(
                new Entity1("entity1"),
                new Entity2("entity2")
        ));
    }

    @Test
    public void readingTest() throws Exception {

        List<BaseEntity> entities = repo.findAll();
        assertThat(entities).hasSize(2);

        entities.forEach(System.out::println);
    }
}