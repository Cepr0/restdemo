package restsdemo.example17;

import lombok.Data;
import restsdemo.base.LongId;

import javax.persistence.Entity;

/**
 * @author Cepro
 * @since 2017-08-07
 */
@Data
@Entity
public class A extends LongId {
    private String name;
}
