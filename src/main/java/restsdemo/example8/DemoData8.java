package restsdemo.example8;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import restsdemo.example8.model.Member;
import restsdemo.example8.model.Project;
import restsdemo.example8.model.Role;
import restsdemo.example8.model.Skill;
import restsdemo.example8.repo.MemberRepo;
import restsdemo.example8.repo.ProjectRepo;
import restsdemo.example8.repo.RoleRepo;
import restsdemo.example8.repo.SkillRepo;

import java.util.List;

import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor
public class DemoData8 {
    
    private final @NonNull SkillRepo skillRepo;
    private final @NonNull RoleRepo roleRepo;
    private final @NonNull MemberRepo memberRepo;
    private final @NonNull ProjectRepo projectRepo;
    
    @Async
    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
        
        List<Skill> skills = skillRepo.save(asList(
                new Skill("skill1"),
                new Skill("skill2"),
                new Skill("skill3"),
                new Skill("skill4"),
                new Skill("skill5")
        ));
        skillRepo.flush();
        
        List<Role> roles = roleRepo.save(asList(
                new Role("role1"),
                new Role("role2"),
                new Role("role3"),
                new Role("role4"),
                new Role("role5")
        ));
        roleRepo.flush();
        
        List<Member> users = memberRepo.save(asList(
                new Member("user1",
                        new Member.MemberSkill(skills.get(0), 1),
                        new Member.MemberSkill(skills.get(1), 2),
                        new Member.MemberSkill(skills.get(2), 3)),
                new Member("user2",
                        new Member.MemberSkill(skills.get(3), 4),
                        new Member.MemberSkill(skills.get(4), 5),
                        new Member.MemberSkill(skills.get(0), 1)),
                new Member("user3",
                        new Member.MemberSkill(skills.get(1), 2),
                        new Member.MemberSkill(skills.get(2), 3),
                        new Member.MemberSkill(skills.get(3), 4)),
                new Member("user4",
                        new Member.MemberSkill(skills.get(4), 5),
                        new Member.MemberSkill(skills.get(0), 1),
                        new Member.MemberSkill(skills.get(1), 2)),
                new Member("user5",
                        new Member.MemberSkill(skills.get(2), 3),
                        new Member.MemberSkill(skills.get(3), 4),
                        new Member.MemberSkill(skills.get(4), 5))
        ));
        memberRepo.flush();
        
        Project project = projectRepo.save(new Project("project1",
                new Project.ProjectSkill(skills.get(0), 1),
                new Project.ProjectSkill(skills.get(1), 1),
                new Project.ProjectSkill(skills.get(2), 1),
                new Project.ProjectSkill(skills.get(3), 1),
                new Project.ProjectSkill(skills.get(4), 1)
        ));

        project.addMembers(
                new Project.ProjectMember(users.get(0), roles.get(0)),
                new Project.ProjectMember(users.get(1), roles.get(1)),
                new Project.ProjectMember(users.get(2), roles.get(2)),
                new Project.ProjectMember(users.get(3), roles.get(3)),
                new Project.ProjectMember(users.get(4), roles.get(4))
        );
        
    }
}
