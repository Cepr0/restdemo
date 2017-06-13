package restsdemo.example9;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
public class BiPosition extends LongId {

    private String title;
    
     @ManyToMany(mappedBy = "positions")
     private final Set<BiWorker> workers = new HashSet<>();
}
