package restsdemo.example14;

import lombok.*;

import javax.persistence.Entity;

/**
 * @author Cepro
 *         2017-07-19
 */
@Data
@AllArgsConstructor
@Entity
public class Entity2 extends BaseEntity {

    private String name;
}