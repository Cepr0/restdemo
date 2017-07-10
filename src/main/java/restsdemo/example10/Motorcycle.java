package restsdemo.example10;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author Cepro
 * @since 2017-07-07
 */
@NoArgsConstructor
@Entity
public class Motorcycle extends Vehicle {
    
    public Motorcycle(String name, Tire... tires) {
        super(name, tires);
    }
}
