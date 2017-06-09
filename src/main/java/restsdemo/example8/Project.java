package restsdemo.example8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Project extends LongId {
    private String name;

    @ElementCollection
    @Column(name = "skill")
    private final Set<ProjectSkill> skills = new HashSet<>();

    @ElementCollection
    @Column(name = "member")
    private final Set<ProjectMember> members = new HashSet<>();
}
