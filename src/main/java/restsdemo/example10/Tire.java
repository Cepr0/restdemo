package restsdemo.example10;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Cepro
 * @since 2017-07-07
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tires")
public class Tire extends LongId {
    
    private String name;
}
