package restsdemo.example1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ParentRepoTest {

    @Autowired
    private ParentRepo parentRepo;
    
    @Test
    public void getDto() throws Exception {
    
        List<DemoDto> dtos = parentRepo.getDto(2L);
        dtos.forEach(System.out::println);
    }
}