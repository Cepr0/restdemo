package restsdemo.example18;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;

/**
 * @author Cepro, 2017-09-04
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Sample extends LongId {

	private String data = "data";
}
