package restsdemo.example1;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import restsdemo.base.LongId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "toys")
public class Toy extends LongId {

    @NotBlank
    @Column(nullable = false)
    @Length(min = 3)
    private String name;
}
