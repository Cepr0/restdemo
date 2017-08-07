package restsdemo.example17;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restsdemo.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Cepro
 * @since 2017-08-07
 */
public class BRepoTest extends BaseTest {
    
    @Autowired
    private BRepo bRepo;
    
    @Before
    public void setUp() throws Exception {
        
        bRepo.save(new B().setA(new A().setName("a1"))
                          .setOtherData("otherData1")
                          .setSomeData("sd1"));
    }
    
    @Test
    public void findById() throws Exception {
    
        BProjection bProjection = bRepo.findById(1L);
        assertThat(bProjection.getA().getName()).isEqualTo("a1");
    }
    
}