package restsdemo.example15;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

/**
 * @author Cepro
 * @since 2017-07-23
 */
@Data
@NoArgsConstructor
@Entity
public class Slave {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true)
    private String name;
    
    @ManyToOne(cascade = {PERSIST, MERGE})
    private Master master;
    
    public Slave(String name, Master master) {
        this.name = name;
        setMaster(master);
    }
    
    public Slave(String name) {
        this.name = name;
    }
    
    public void setMaster(Master master) {
        if(this.master != null) {
            this.master.getSlaves().remove(this);
        }
        
        if (master != null && !master.getSlaves().contains(this)) {
            master.getSlaves().add(this);
        }
        
        this.master = master;
    }
}
