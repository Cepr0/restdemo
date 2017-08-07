package restsdemo.example17;

import lombok.Data;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static javax.persistence.CascadeType.PERSIST;

/**
 * @author Cepro
 * @since 2017-08-07
 */
@Data
@Entity
public class B extends LongId {
    
    private String someData;
    private String otherData;
    @ManyToOne(optional = false, cascade = PERSIST)
    private A a;
}
