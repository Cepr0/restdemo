package restsdemo.example11;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import restsdemo.base.LongId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cepro
 * @since 2017-07-08
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Primarry extends LongId {
    
    private String name;
    
    @OneToMany(mappedBy = "primary", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapKey(name = "type")
    private final Map<String, Secondary> secondaries = new HashMap<>();
    
    public Primarry addSecondaries(Secondary... secondaries) {
        for (Secondary secondary : secondaries) {
            secondary.setPrimary(this);
            this.secondaries.put(secondary.getType(), secondary);
        }
        return this;
    }
    
    public interface Repo extends JpaRepository<Primarry, Long> {
    }
}
