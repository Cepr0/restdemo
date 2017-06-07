package restsdemo.example7;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
