package restsdemo.example9;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BiPositionRepoTest {

    @Autowired
    private BiPositionRepo positionRepo;

    @Autowired
    private BiWorkerRepo workerRepo;

    @Test
    public void findAll() throws Exception {
        List<BiPosition> positions = positionRepo.findAll();
        assertThat(positions.size(), is(2));

        assertThat(positions.stream().map(position -> position.getWorkers().size()).collect(toList()), hasItems(1, 2));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void canNotDelete() throws Exception {
        positionRepo.delete(2L);
        positionRepo.flush();
    }

    @Test
    public void delete() throws Exception {
        BiPosition position = positionRepo.getOne(2L);

        BiWorker worker = workerRepo.getOne(2L);
        worker.getPositions().remove(position);
        workerRepo.saveAndFlush(worker);

        positionRepo.delete(2L);
        positionRepo.flush();

        assertThat(workerRepo.findAll().size(), is(2));
    }

    @Test
    public void delete2() throws Exception {
        workerRepo.delete(2L);
        workerRepo.flush();

        positionRepo.delete(2L);
        positionRepo.flush();
        assertThat(positionRepo.findAll().size(), is(1));
    }
}