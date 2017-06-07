package restsdemo.example6;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
