package restsdemo.example6;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("select distinct u from User u join u.orderrs o join u.roles r where r = ?1 and (o.state is null or o.state = ?2)")
    List<User> getUsers(User.Role role, Orderr.State state);
}
