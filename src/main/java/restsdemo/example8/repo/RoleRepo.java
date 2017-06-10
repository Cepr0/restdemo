package restsdemo.example8.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import restsdemo.example8.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
