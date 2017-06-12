package restsdemo.example9;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;

/**
 *
 */
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Position extends LongId {

    private String title;
    
    // @ManyToMany(mappedBy = "positions")
    // private final Set<Worker> workers = new HashSet<>();
}
