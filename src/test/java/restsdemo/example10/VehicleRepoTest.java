package restsdemo.example10;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Cepro
 * @since 2017-07-07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VehicleRepoTest {
    
    @Autowired
    private VehicleRepo vehicleRepo;
    
    @Test
    public void findByGallonsIsGreaterThan() throws Exception {
        List<Tire> tires = vehicleRepo.findByGallonsIsGreaterThan(100);
        assertThat(tires.size(), is(2));
        assertThat(tires.get(0).getName(), is("tire1"));
        assertThat(tires.get(1).getName(), is("tire2"));
        tires.forEach(System.out::println);
    }
}