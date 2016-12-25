package restsdemo.domain.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * Base class for entity with {@link LocalDate} id.
 * @author Cepro, 2016-12-10
 */
@MappedSuperclass
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DateId extends BaseEntity<LocalDate> {
    
    @Id
    @Column(columnDefinition = "date default now()", unique = true, nullable = false)
    @JsonProperty(index = 0)
    private final LocalDate id;
    
    public DateId() {
        id = LocalDate.now();
    }
}
