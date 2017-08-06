package restsdemo.example15;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static javax.persistence.CascadeType.*;

/**
 * @author Cepro
 * @since 2017-07-23
 */
@Data
@NoArgsConstructor
@ToString(exclude = "slaves")
@Entity
public class Master {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true)
    private String name;
    
    @OneToMany(mappedBy = "master", cascade = {PERSIST, MERGE})
    private List<Slave> slaves = new ArrayList<>();
    
    public Master(String name, Slave... slaves) {
        this.name = name;
        setSlaves(asList(slaves));
    }
    
    public Master(String name) {
        this.name = name;
    }
    
    public Master setSlaves(List<Slave> slaves) {
        removeSlaves();
        return addSlaves(slaves);
    }
    
    public Master addSlaves(List<Slave> slaves) {
        for (Slave slave : slaves) {
            if (slave.getMaster() != this ) {
                slave.setMaster(this);
            }
            if (!this.slaves.contains(slave)) {
                this.slaves.add(slave);
            }
        }
        return this;
    }
    
    private Master removeSlaves(List<Slave> slaves) {
        
        ArrayList<Slave> tempList = new ArrayList<>(slaves);
        tempList.retainAll(this.slaves);
        cleanSlaves(tempList);
        return this;
    }
    
    public Master removeSlaves() {
        
        ArrayList<Slave> tempList = new ArrayList<>(this.slaves);
        // We cannot use loop over the 'this.slaves' collection because every invocation of `slave.setMaster(null)
        // leads to removing a `slave` from this collection
        cleanSlaves(tempList);
        return this;
    }
    
    private void cleanSlaves(ArrayList<Slave> tempList) {
        tempList.forEach(slave -> slave.setMaster(null));
        this.slaves.clear();
    }
}
