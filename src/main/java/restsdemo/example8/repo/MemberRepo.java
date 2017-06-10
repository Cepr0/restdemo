package restsdemo.example8.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import restsdemo.example8.model.Member;

public interface MemberRepo extends JpaRepository<Member, Long> {
}
