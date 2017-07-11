package restsdemo.example13;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Cepro
 *         2017-07-11
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Figure extends LongId {

    @Min(value = 5, message = "Panic! Width must be at least 5!")
    @Max(value = 25, message = "Panic! Width must be not greater than 25!")
    private Integer width;

    @Min(value = 10, message = "Panic! Height must be at least 10!")
    @Max(value = 50, message = "Panic! Height must be not greater than 50!")
    private Integer height;

    @RepositoryRestResource(exported = false)
    public interface Repo extends JpaRepository<Figure, Long> {
    }
}
