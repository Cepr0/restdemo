package restsdemo.example15;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restsdemo.BaseTest;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Cepro
 * @since 2017-08-05
 */
public class MasterSlaveTest extends BaseTest {
    
    @Autowired
    private MasterRepo masterRepo;
    
    @Before
    public void setUp() throws Exception {
    
        Slave slave1 = new Slave().setName("slave1");
        Slave slave2 = new Slave().setName("slave2");
        Master master = new Master().setName("master1").setSlaves(asList(slave1, slave2));
    
        masterRepo.save(master);
    }
    
    @Test
    public void get() throws Exception {
        Master m1 = masterRepo.getOne(1L);
        assertThat(m1).isNotNull();
    
        Optional<MasterDto> masterDto = masterRepo.findById(1L);
        if (masterDto.isPresent()) {
            MasterDto dto = masterDto.get();
            assertThat(dto).isNotNull();
            assertThat(dto.getName()).isEqualTo("master1");
            assertThat(dto.getSlaves()).hasSize(2);
            assertThat(dto.getSlaveNames()).hasSize(2);
        }
    }
}