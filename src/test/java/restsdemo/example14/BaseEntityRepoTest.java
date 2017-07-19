package restsdemo.example14;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restsdemo.BaseTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Cepro
 *         2017-07-19
 */
public class BaseEntityRepoTest extends BaseTest {

    @Autowired
    private BaseEntityRepo repo;

    @Test
    public void baseEntityTest() throws Exception {

        BaseEntity entity1 = new Entity1("entity1");
        BaseEntity entity2 = new Entity2("entity2");

        repo.save(entity1);
        repo.save(entity2);

        List<BaseEntity> entities = repo.findAll();
        assertThat(entities).hasSize(2);

        entities.forEach(System.out::println);
    }
}