package restsdemo.example9;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import restsdemo.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 *
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WorkerRepoTest extends BaseTest {

    @Autowired private WorkerRepo workerRepo;
    @Autowired private PositionRepo positionRepo;
    
    @Test
    public void findByPositions() throws Exception {
        Position position = positionRepo.getOne(1L);
        List<Worker> workers = workerRepo.findByPositions(position);
        assertThat(workers.size(), is(2));
    }

    @Test
    public void findByName() throws Exception {
        List<Worker> workers = workerRepo.findByName("worker2");
        List<String> titles = workers.get(0).getPositions().stream().map(Position::getTitle).collect(Collectors.toList());
        assertThat(titles, contains("position1", "position2"));
    }

    @Test
    public void findWorkerByName() throws Exception {
        Worker worker2 = workerRepo.findWorkerByName("worker2");
        assertThat(worker2.getPositions().size(), is(2));

    }
}