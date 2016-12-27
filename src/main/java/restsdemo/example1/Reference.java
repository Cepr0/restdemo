package restsdemo.example1;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import restsdemo.base.LongId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Cepro, 2016-12-24
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "references")
public class Reference extends LongId {
    
    @NotEmpty
    @Column(nullable = false)
    @Length(min = 3)
    @NonNull
    private String description;
}
