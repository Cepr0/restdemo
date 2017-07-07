package restsdemo.example10;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * @author Cepro
 * @since 2017-07-07
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "vehicles")
public abstract class Vehicle extends LongId {
    
    private String name;

    @OneToMany
    private final Set<Tire> tires = new HashSet<>();
    
    public Vehicle(String name, Tire... tires) {
        this.name = name;
        this.tires.addAll(asList(tires));
    }
}
