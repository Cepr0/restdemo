package restsdemo.example16;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import restsdemo.base.LongId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Cepro
 * @since 2017-08-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Man extends LongId {
    
    private String name;
    
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Way way;
}
