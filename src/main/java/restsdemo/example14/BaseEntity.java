package restsdemo.example14;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Cepro
 *         2017-07-19
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}
