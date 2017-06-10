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

import static java.util.stream.Collectors.*;

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
    
    // Lazy
    default Map<Member, Set<Member.MemberSkill>> getMembersWithMemberSkillsByProject(Project project) {
        return getProjectMembersByProject(project)
                .collect(toMap(member -> member, Member::getSkills));
    }
    
    @RestResource(exported = false)
    @Query("select m.member as member, s.skill as skill, s.level as level from Project p join p.members m join m.member.skills s where p = ?1")
    Stream<Member.MemberSkillLevel> getMembersSkillsAndLevelsByProject(Project project);
    
    // Eager
    default Map<Member, Set<Member.MemberSkill>> getSkillAndLevelByMember(Project project) {
        return getMembersSkillsAndLevelsByProject(project)
                .collect(groupingBy(
                        Member.MemberSkillLevel::getMember,
                        mapping(el -> new Member.MemberSkill(el.getSkill(), el.getLevel()), toSet())));
    }
}
