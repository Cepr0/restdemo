package restsdemo.example15;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import restsdemo.BaseTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Cepro
 * @since 2017-08-05
 */
public class MasterSlaveTest extends BaseTest {
    
    @Autowired
    private MasterRepo masterRepo;
    
    @Autowired
    private SlaveRepo slaveRepo;

    @Before
    public void setUp() throws Exception {
    
        Slave slave1 = new Slave("slave1");
        Slave slave2 = new Slave("slave2");
        Master master1 = new Master("master1", slave1, slave2);

        Slave slave3 = new Slave("slave30");
        Slave slave4 = new Slave("slave40");
        Master master2 = new Master("master20", slave3, slave4);

        masterRepo.save(asList(master1, master2));
    }
    
    @Test
    public void getMaster() throws Exception {
        Master m = masterRepo.findByName("master1").orElseThrow(Exception::new);
        assertThat(m).isNotNull();
        assertThat(m.getSlaves()).hasSize(2);
    }
    
    @Test
    public void getMasterDtoTest() {
        Optional<MasterDto> masterDto = masterRepo.findById(1L);
        if (masterDto.isPresent()) {
            MasterDto dto = masterDto.get();
            assertThat(dto).isNotNull();
            assertThat(dto.getName()).isEqualTo("master1");
            assertThat(dto.getSlaves()).hasSize(2);
            assertThat(dto.getSlaveNames()).hasSize(2);
        }
    }

    @Test
    public void getDtosTest() throws Exception {

        List<MasterDto> dtos = masterRepo.findBy();
        assertThat(dtos).isNotNull();
        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getSlaves()).hasSize(2);
    }

    @Test
    public void updateMasterTest() throws Exception {
        
        Slave s3 = new Slave("slave3");
        Slave s4 = new Slave("slave4");
    
        Master m = masterRepo.findByName("master1").orElseThrow(Exception::new);
        m.setSlaves(asList(s3, s4));
        masterRepo.save(m);
    
        m = masterRepo.findByName("master1").orElseThrow(Exception::new);
        assertThat(m).isNotNull();
        assertThat(m.getSlaves()).hasSize(2);
        assertThat(m.getSlaves().stream().map(Slave::getName).collect(Collectors.toList()))
                .containsOnly("slave3", "slave4");
    
        assertThat(slaveRepo.findAll()).hasSize(6);
    }
    
    @Test
    public void getSlaveTest() throws Exception {
        assertThat(slaveRepo.findAll()).hasSize(4);
    }
    
    @Test
    public void createSlaveTest() throws Exception {
        Master m = masterRepo.findByName("master1").orElseThrow(Exception::new);
        slaveRepo.save(new Slave("slave3", m));
        
        List<Slave> slaves = slaveRepo.findAll();
        assertThat(slaves).hasSize(5);
        
        Optional<Slave> slaveOptional = slaves.stream().filter(slave -> "slave3".equals(slave.getName())).findFirst();
        assertTrue(slaveOptional.isPresent());
        
        Slave slave = slaveOptional.get();
        Master master = slave.getMaster();
        assertThat(master.getSlaves()).hasSize(3);
    }
    
    @Test
    public void updateSlaveTest() throws Exception {
        
        Slave s2 = slaveRepo.findByName("slave2").orElseThrow(Exception::new);
        Master m2 = new Master("master2");
        s2.setMaster(m2);
        slaveRepo.save(s2);
    
        Slave updatedSlave = slaveRepo.findByName("slave2").orElseThrow(Exception::new);
        assertThat(updatedSlave.getMaster().getName()).isEqualTo("master2");
    
        Master master2 = masterRepo.findByName("master2").orElseThrow(Exception::new);
        assertThat(master2.getSlaves()).hasSize(1);
    
        Master master1 = masterRepo.findByName("master1").orElseThrow(Exception::new);
        assertThat(master1.getSlaves()).hasSize(1);
    }
}