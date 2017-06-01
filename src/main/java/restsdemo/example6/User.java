package restsdemo.example6;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

/**
 *
 */
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Entity
public class User extends LongId {
    
    private final String name;
    
    private final Role role;
    
    @OneToMany(mappedBy = "user")
    private final List<Orderr> orderrs = new ArrayList<>();
    
    public enum Role {
        ROLE1, ROLE2
    }
}
