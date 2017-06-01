package restsdemo.example5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Question extends LongId {

    private String text;
}
