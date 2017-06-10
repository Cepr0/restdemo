package restsdemo.example8.model;

import lombok.*;
import restsdemo.base.LongId;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Project extends LongId {
    
    private String name;

    @ElementCollection
    private final Set<ProjectSkill> skills = new HashSet<>();

    @ElementCollection
    private final Set<ProjectMember> members = new HashSet<>();
    
    public Project(String name, ProjectSkill... skills) {
        this.name = name;
        this.skills.addAll(asList(skills));
    }
    
    public void addMembers(ProjectMember... members) {
        this.members.addAll(asList(members));
    }
    
    @Value
    @RequiredArgsConstructor
    @Embeddable
    public static class ProjectMember {
    
        @OneToOne(optional = false)
        private final Member member;
    
        @ManyToOne(optional = false)
        private final Role role;
    
        public ProjectMember() {
            this(null, null);
        }
    }
    
    @Value
    @RequiredArgsConstructor
    @Embeddable
    public static class ProjectSkill {
    
        @OneToOne(optional = false)
        private final Skill skill;
    
        @Min(1)
        @Max(5)
        private final Integer minLevel;
        
        public ProjectSkill() {
            this(null, null);
        }
    }
}
