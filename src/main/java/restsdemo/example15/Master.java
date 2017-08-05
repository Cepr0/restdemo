package restsdemo.example15;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

/**
 * @author Cepro
 * @since 2017-07-23
 */
@Data
@ToString(exclude = "slaves")
@Entity
public class Master {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy = "master", cascade = {PERSIST, MERGE})
    private List<Slave> slaves = new ArrayList<>();
    
    public Master setSlaves(List<Slave> slaves) {
        slaves.forEach(slave -> slave.setMaster(this)); // link new slaves to this master
        this.slaves.forEach(slave -> slave.setMaster(null)); // unlink prev slaves
        this.slaves.clear();
        this.slaves.addAll(slaves);
        return this;
    }
}
