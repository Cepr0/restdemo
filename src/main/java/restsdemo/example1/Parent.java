package restsdemo.example1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import restsdemo.base.LongId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

/**
 * @author Cepro, 2016-12-25
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parents")
public class Parent extends LongId {
    
    @NotBlank
    @Column(nullable = false)
    private String name = "Undefine";
    
    @OneToMany(cascade = {PERSIST, MERGE})
    private List<Child> children = new ArrayList<>();
    
    public Parent(String name, Child... children) {
        this.name = name;
        this.children.addAll(Arrays.asList(children));
    }
}
