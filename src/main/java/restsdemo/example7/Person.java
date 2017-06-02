package restsdemo.example7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 *
 */
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(PersonListener.class)
@Entity
public class Person extends LongId {

    private String name;
}
