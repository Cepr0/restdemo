package restsdemo.example12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

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

    @OneToMany
    @JoinColumn(name = "two_id")
    private final Set<Three> threes = new HashSet<>();

    public Two(String name, Three... threes) {
        this.name = name;
        this.threes.addAll(asList(threes));
    }

    public interface Repo extends JpaRepository<Two, Long> {
    }
}
