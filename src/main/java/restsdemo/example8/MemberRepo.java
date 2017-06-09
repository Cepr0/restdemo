package restsdemo.example8;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface MemberRepo extends JpaRepository<Member, Long> {
}
