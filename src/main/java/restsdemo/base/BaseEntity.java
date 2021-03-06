package restsdemo.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.hateoas.Identifiable;

import java.io.Serializable;

/**
 * Base class for entity implementations.
 * @author Cepro, 2016-12-10
 */
public abstract class BaseEntity<ID extends Serializable> implements Identifiable<ID> {
}
