package restsdemo.example16;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import restsdemo.base.LongId;

import javax.persistence.Entity;

/**
 * @author Cepro
 * @since 2017-08-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Way extends LongId {
    
    private String name;
}
