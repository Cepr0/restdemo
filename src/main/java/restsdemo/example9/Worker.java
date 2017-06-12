package restsdemo.example9;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Worker extends LongId {

    private String name;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final Set<Position> positions = new HashSet<>();
    
    // public Worker addPositions(Position... positions) {
    //
    //     for (Position position : positions) {
    //         position.getWorkers().add(this);
    //         this.positions.add(position);
    //     }
    //     return this;
    // }
    //
    // public Worker removePositions(Position... positions) {
    //
    //     for (Position position : positions) {
    //         position.getWorkers().remove(this);
    //         this.positions.remove(position);
    //     }
    //
    //     return this;
    // }
}
