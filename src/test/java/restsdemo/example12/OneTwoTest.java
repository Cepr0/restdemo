package restsdemo.example12;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import restsdemo.BaseTest;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Cepro
 * @since 2017-07-09
 */
public class OneTwoTest extends BaseTest {
    
    @Autowired
    private One.Repo oneRepo;
    
    @Autowired
    private Two.Repo twoRepo;
    
    @Before
    public void setUp() throws Exception {
        
        List<Two> twos = twoRepo.save(asList(
                new Two("two1"),
                new Two("two2"),
                new Two("two3"),
                new Two("two4"),
                new Two("two5")
        ));
        
        oneRepo.save(asList(
                new One("one1", twos.get(0), twos.get(1)),
                new One("one2", twos.get(2), twos.get(3), twos.get(4))
        ));
        oneRepo.flush();
    }
    
    @Test
    public void findAll() throws Exception {
    
        List<One> ones = oneRepo.findAll();
        assertThat(ones).hasSize(2);

        ones.forEach(one -> {
            Set<Two> twos = one.getTwos();
            assertThat(twos).isNotNull();
            assertThat(twos.size()).isGreaterThan(0);
            twos.forEach(two -> assertThat(two).isNotNull());
        });

        assertThat(twoRepo.findAll()).hasSize(5);
    }
    
    @Test
    public void findAllByTwosContains() throws Exception {
    
        Two two1 = twoRepo.getOne(1L);
        List<One> ones = oneRepo.findAllByTwosContains(two1);
        assertThat(ones).hasSize(1);

        Two two3 = twoRepo.getOne(3L);
        ones = oneRepo.findAllByTwosContains(two3);
        assertThat(ones).hasSize(1);
    }
    
    @Test
    public void getTwosByOneName() throws Exception {
        List<Two> twos = oneRepo.getTwosByOneName("one1");
        assertThat(twos).hasSize(2);
        assertThat(twos).containsOnly(twoRepo.getOne(1L), twoRepo.getOne(2L));
    }
    
    @Test
    public void getWithSpecification() throws Exception {
    
        Specification<One> equalsTwoName = (one, query, cb) -> {
            Join<One, Two> joinTwos = one.join("twos");
            return cb.equal(joinTwos.get("name"), "two2");
        };
       
        List<One> ones = oneRepo.findAll(equalsTwoName);
        assertThat(ones).hasSize(1);
        assertThat(ones.get(0).getName()).isEqualTo("one1");
    
        Specification<One> likeOneNameAndTwoName = (one, query, cb) -> {
            Join<One, Two> joinTwos = one.join("twos");
            return cb.and(
                    cb.like(one.get("name"), "%1%"),
                    cb.like(joinTwos.get("name"), "%2%")
            );
        };
    
        ones = oneRepo.findAll(likeOneNameAndTwoName);
        assertThat(ones).hasSize(1);
        assertThat(ones.get(0).getName()).isEqualTo("one1");
    }
}