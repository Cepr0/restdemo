package restsdemo.example12;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import restsdemo.BaseTest;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.*;

/**
 * @author Cepro
 * @since 2017-07-09
 */
public class OneTwoTest extends BaseTest {
    
    @Autowired
    private One.Repo oneRepo;
    
    @Autowired
    private Two.Repo twoRepo;

    @Autowired
    private Three.Repo threeRepo;

    @Before
    public void setUp() throws Exception {

        List<Three> threes = threeRepo.save(asList(
                new Three("three1"),
                new Three("three2"),
                new Three("three3"),
                new Three("three4"),
                new Three("three5")
        ));

        List<Two> twos = twoRepo.save(asList(
                new Two("two1", threes.get(0), threes.get(1), threes.get(2)),
                new Two("two2"),
                new Two("two3", threes.get(3), threes.get(4)),
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

        Specification<One> twoJoins = (one, query, cb) ->  {
            Join<One, Two> twos = one.join("twos");
            Join<Two, Three> threes = twos.join("threes");
            return threes.get("name").in(asList("three1", "three5"));
        };

        ones = oneRepo.findAll(twoJoins, new Sort(DESC, "name"));
        assertThat(ones).hasSize(2);

        PageRequest pageRequest = new PageRequest(0, 2, new Sort(Sort.Direction.DESC, "name"));
        Page<One> onePage = oneRepo.findAll(twoJoins, pageRequest);
        assertThat(onePage.getContent()).hasSize(2);
    }

    @Test
    public void getOnes() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2, new Sort(Sort.Direction.DESC, "name"));
        Page<One> onePage = oneRepo.getOnes("three5", pageRequest);
        assertThat(onePage.getContent()).hasSize(1);
    }
}