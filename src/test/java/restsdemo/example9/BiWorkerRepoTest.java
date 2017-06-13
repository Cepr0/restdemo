package restsdemo.example9;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class BiWorkerRepoTest {

    @Autowired
    private BiWorkerRepo workerRepo;

    @Test
    public void findAll() throws Exception {
        List<BiWorker> workers = workerRepo.findAll();
        assertThat(workers.size(), is(2));

        assertThat(workers.stream().map(worker -> worker.getPositions().size()).collect(toList()), hasItems(1, 2));
    }
}