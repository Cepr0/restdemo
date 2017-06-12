package restsdemo.example9;

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
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WorkerRepoTest {

    @Autowired
    private WorkerRepo workerRepo;

    @Autowired
    private PositionRepo positionRepo;
    
    @Test
    public void findByPositions() throws Exception {
        Position position = positionRepo.getOne(1L);
        List<Worker> workers = workerRepo.findByPositions(position);
        assertThat(workers.size(), is(2));
    }
}