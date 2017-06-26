package restsdemo.example4;

import org.springframework.data.rest.core.annotation.RestResource;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 */
@RestResource(rel = "criteria", path = "criteria")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "criteria")
public abstract class Criterion extends LongId {
}
