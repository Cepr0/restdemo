package restsdemo.example7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 */
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Token extends LongId {
    
    @OneToOne
    private Person person;
    
    private String text;
}
