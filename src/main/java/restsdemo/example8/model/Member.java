package restsdemo.example8.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    
    @JsonUnwrapped // https://stackoverflow.com/a/44719861
    @ElementCollection
    private final Set<MemberSkill> skills = new HashSet<>();
    
    public Member(String name, MemberSkill... skills) {
        this.name = name;
        this.skills.addAll(asList(skills));
    }
    
    @Value
    @RequiredArgsConstructor
    @Embeddable
    @JsonSerialize(using = SkillSerializer.class) // https://stackoverflow.com/a/44719861
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
