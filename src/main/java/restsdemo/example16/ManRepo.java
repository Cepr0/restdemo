package restsdemo.example16;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Cepro
 * @since 2017-08-03
 */
public interface ManRepo extends JpaRepository<Man, Long> {
    
    @Query("select m from Man m join fetch m.way where m.id = ?1")
    Man fetchOne(Long id);
}
