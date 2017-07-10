package restsdemo.example11;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * @author Cepro
 * @since 2017-07-08
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
public class Secondary extends LongId {
    
    private String type;
    private String note;
    
    @ManyToOne
    private Primarry primary;
    
    public Secondary(String type, String note) {
        this.type = type;
        this.note = note;
    }
    
    public interface Repo extends JpaRepository<Secondary, Long> {
        
        @EntityGraph(attributePaths = {"primary"})
        @Override
        List<Secondary> findAll(Sort sort);
    }
}
