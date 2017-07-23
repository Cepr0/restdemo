package restsdemo.example15;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Cepro
 * @since 2017-07-23
 */
@Data
@Entity
public class Slave {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    @ManyToOne
    private Master master;
}
