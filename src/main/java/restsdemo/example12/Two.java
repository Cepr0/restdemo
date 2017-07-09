package restsdemo.example12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import restsdemo.base.LongId;

import javax.persistence.Entity;

/**
 * @author Cepro
 * @since 2017-07-09
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Two extends LongId {
    
    private String name;
    
    public interface Repo extends JpaRepository<Two, Long> {
    }
}
