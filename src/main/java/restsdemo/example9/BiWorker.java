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
public class BiWorker extends LongId {

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final Set<BiPosition> positions = new HashSet<>();

    @PrePersist
    public void addPositions() {
        positions.forEach(position -> position.getWorkers().add(this));
    }

    @PreRemove
    public void removePositions() {
        positions.forEach(position -> position.getWorkers().remove(this));
    }
}
