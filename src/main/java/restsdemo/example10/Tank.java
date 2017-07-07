package restsdemo.example10;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * @author Cepro
 * @since 2017-07-07
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tank extends Vehicle {
    
    private int gallons;
    
    public Tank(String name, int gallons, Tire... tires) {
        super(name, tires);
        this.gallons = gallons;
    }
}
