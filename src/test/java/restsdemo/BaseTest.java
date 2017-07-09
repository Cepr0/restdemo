package restsdemo;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cepro
 * @since 2017-07-09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public abstract class BaseTest {
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
}
