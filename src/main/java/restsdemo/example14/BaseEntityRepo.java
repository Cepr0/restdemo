package restsdemo.example14;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Cepro
 *         2017-07-19
 */
public interface BaseEntityRepo extends JpaRepository<BaseEntity, Long> {
}
