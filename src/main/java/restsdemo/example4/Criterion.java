package restsdemo.example4;

import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "criteria")
public abstract class Criterion extends LongId {
}
