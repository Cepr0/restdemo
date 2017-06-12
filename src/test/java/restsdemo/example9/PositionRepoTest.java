package restsdemo.example9;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PositionRepoTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Autowired
    private WorkerRepo workerRepo;
    
    @Autowired
    private PositionRepo positionRepo;
    
    @Test(expected = DataIntegrityViolationException.class)
    public void canNotDelete() throws Exception {
        positionRepo.delete(2L);
        positionRepo.flush();
    }
    
    @Test
    public void delete() throws Exception {
        Position position = positionRepo.getOne(2L);
    
        Worker worker = workerRepo.getOne(2L);
        worker.getPositions().remove(position);
        workerRepo.saveAndFlush(worker);
        
        positionRepo.delete(2L);
        positionRepo.flush();
    
        assertThat(workerRepo.findAll().size(), is(2));
    }
}