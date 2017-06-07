package restsdemo.example6;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 *
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("select distinct u from User u join u.orderrs o join u.roles r where r = ?1 and (o.state is null or o.state = ?2)")
    List<User> getUsers(User.Role role, Orderr.State state);

    @EntityGraph(attributePaths = {"roles", "orderrs"})
    @Override
    Page<User> findAll(Pageable pageable);
}
