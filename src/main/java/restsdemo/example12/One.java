package restsdemo.example12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
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
public class One extends LongId {
    
    private String name;
    
    @OneToMany
    private final Set<Two> twos = new HashSet<>();
    
    public One(String name, Two... twos) {
        this.name = name;
        this.twos.addAll(asList(twos));
    }
    
    public interface Repo extends JpaRepository<One, Long>, JpaSpecificationExecutor<One> {
        List<One> findAllByTwosContains(Two two);
        
        @Query("select t from One o join o.twos t where o.name = ?1")
        List<Two> getTwosByOneName(String oneName);
    }
}