package restsdemo.example7;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface TokenRepository extends JpaRepository<Token, Long> {
    void deleteByPerson(Person person);
}
