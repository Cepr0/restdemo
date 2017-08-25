package restsdemo.example9;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import restsdemo.BaseTest;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(workers).hasSize(2);
    }

    @Test
    public void findByName() throws Exception {
        List<Worker> workers = workerRepo.findDistinctByName("worker2");
        assertThat(workers).hasSize(1);
        List<String> titles = workers.get(0).getPositions().stream().map(Position::getTitle).collect(toList());
        assertThat(titles).containsOnly("position1", "position2");
    }

    @Test
    public void findWorkerByName() throws Exception {
        Worker worker2 = workerRepo.findWorkerByName("worker2");
        assertThat(worker2.getPositions()).hasSize(2);
    }

    @Test
    public void findDistinctByPositionsContains() throws Exception {
        List<Position> positions = positionRepo.findAll();
        List<Worker> workers = workerRepo.findDistinctByPositionsIn(asList(
            positions.get(0),
            positions.get(1)
        ));

        assertThat(workers).hasSize(3);
    }
}