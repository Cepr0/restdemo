package restsdemo.example8.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import restsdemo.example8.model.Member;
import restsdemo.example8.model.Project;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@SuppressWarnings({"SpringDataRepositoryMethodReturnTypeInspection", "SpringDataMethodInconsistencyInspection"})
public interface ProjectRepo extends JpaRepository<Project, Long> {

    @RestResource(exported = false)
    @Query("select distinct " +
                "s.skill.name " +
            "from Project p " +
                "join p.members m " +
                "join m.member.skills s " +
            "where " +
            "   p = ?1 " +
            "order by " +
            "   s.skill.name")
    List<String> getSkillsByProject(Project project);
    
    @RestResource(exported = false)
    @Query("select m.member from Project p join p.members m where p = ?1")
    Stream<Member> getProjectMembersByProject(Project project);
    
    default Map<Member, Set<Member.MemberSkill>> getMembersWithSkillsByProject(Project project) {
        return getProjectMembersByProject(project)
                .collect(toMap(member -> member, Member::getSkills));
    }
}
