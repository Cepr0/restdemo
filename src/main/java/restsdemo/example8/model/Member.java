package restsdemo.example8.model;

import lombok.*;
import restsdemo.base.LongId;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class Member extends LongId {

    private String name;

    @ElementCollection
    private final Set<MemberSkill> skills = new HashSet<>();
    
    public Member(String name, MemberSkill... skills) {
        this.name = name;
        this.skills.addAll(asList(skills));
    }
    
    @Value
    @RequiredArgsConstructor
    @Embeddable
    public static class MemberSkill {
    
        @ManyToOne(optional = false)
        private final Skill skill;
    
        @Min(1)
        @Max(5)
        private final Integer level;
        
        public MemberSkill() {
            this(null, null);
        }
    }
    
    public interface MemberSkillLevel {
        Member getMember();
        Skill getSkill();
        Integer getLevel();
    }
}
