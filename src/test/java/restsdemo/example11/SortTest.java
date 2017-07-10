package restsdemo.example11;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * @author Cepro
 * @since 2017-07-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SortTest {

    @Autowired
    private Secondary.Repo secondaryRepo;
    
    @Autowired
    private Primarry.Repo primaryRepo;

    @Test
    public void sort() throws Exception {
    
        Secondary sec1 = new Secondary("type1", "note1");
        Secondary sec2 = new Secondary("type2", "note2");
        Secondary sec3 = new Secondary("type1", "note3");
        Secondary sec4 = new Secondary("type2", "note4");
    
        List<Primarry> primaries = primaryRepo.save(asList(
                new Primarry("primary1").addSecondaries(sec1, sec2),
                new Primarry("primary2").addSecondaries(sec3, sec4)
        ));
        
        Sort sort = new Sort(new Sort.Order("type"), new Sort.Order("note"));
        List<Secondary> list = secondaryRepo.findAll(sort);
    
        assertThat(list.size(), is(4));
        assertThat(list, contains(sec1, sec3, sec2, sec4));
    }
}