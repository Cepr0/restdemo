package restsdemo.example6;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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
    private final List<Role> roles = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private final List<Orderr> orderrs = new ArrayList<>();

    public User(String name, Role... roles) {
        this.name = name;
        this.roles.addAll(asList(roles));
    }

    public enum Role {
        ROLE1, ROLE2
    }
}
