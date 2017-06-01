package restsdemo.example6;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 */
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Entity
public class Orderr extends LongId {
    
    private final String title;
    
    @ManyToOne
    private final User user;
    
    private final State state;
    
    public enum State {
        STATE1, STATE2
    }
}
