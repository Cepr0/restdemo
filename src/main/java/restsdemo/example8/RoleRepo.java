package restsdemo.example8;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface RoleRepo extends JpaRepository<Role, Long> {
}
