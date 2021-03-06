package restsdemo.example12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

    private String title;
    private String name;
    
    @OneToMany
    @JoinColumn(name = "one_id")
    private final Set<Two> twos = new HashSet<>();
    
    public One(String title, String name, Two... twos) {
        this.title = title;
        this.name = name;
        this.twos.addAll(asList(twos));
    }

    @RepositoryRestResource
    public interface Repo extends JpaRepository<One, Long>, JpaSpecificationExecutor<One>, QueryDslPredicateExecutor<One> {
        List<One> findAllByTwosContains(Two two);
        
        @Query("select t from One o join o.twos t where o.name = ?1")
        List<Two> getTwosByOneName(String oneName);

        @Query("select o from One o join o.twos t join t.threes r where r.title = ?1")
        Page<One> getOnes(String threeName, Pageable pageable);

        @Query("select o from One o join fetch o.twos t")
        List<One> getOnesAdTwos();

        @Query("select o from One o where concat(o.title, ' ', o.name) like concat('%', ?1, ' ', ?2, '%')")
        List<One> getWithTitleAndName(String title, String name);

        @Query("select o as one, t as two from One o, Two t where exists (select t from Two t where substring(t.name, 4, 1) = substring(o.name, 4, 1)) or not exists (select t from Two t where substring(t.name, 4, 1) = substring(o.name, 4, 1))")
        List<OneTwo> getOnesWithUnrelatedJoin();
    }

    public interface OneTwo {
        One getOne();
        Two getTwo();
    }
}
