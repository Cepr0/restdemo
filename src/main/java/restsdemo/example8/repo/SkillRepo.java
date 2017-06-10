package restsdemo.example8.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import restsdemo.example8.model.Skill;

public interface SkillRepo extends JpaRepository<Skill, Long> {
}
