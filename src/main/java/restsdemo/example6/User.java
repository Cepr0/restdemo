package restsdemo.example6;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.Description;
import restsdemo.base.LongId;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 *
 */
@NoArgsConstructor(force = true)
@Getter
@Setter
@Entity
public class User extends LongId {
    
    @Description("User name (from annotation)")
    private final String name;

    @ElementCollection
    @Column(name = "role")
    private final Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private final Set<Orderr> orderrs = new HashSet<>();

    public User(String name, Role... roles) {
        this.name = name;
        this.roles.addAll(asList(roles));
    }

    public enum Role {
        ROLE1, ROLE2
    }
}
