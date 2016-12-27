package restsdemo.example1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static javax.persistence.CascadeType.MERGE;

/**
 * @author Cepro, 2016-12-25
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parents")
public class Parent extends LongId {
    
    @NonNull
    @Column(nullable = false)
    private String name = "Undefine";
    
    @NonNull
    @OneToMany(cascade = MERGE)
    @Column(unique = true)
    @OrderColumn
    private List<Child> children = new ArrayList<>();
    
    public Parent(String name, Child... children) {
        this.name = requireNonNull(name);
        this.children.addAll(Arrays.asList(requireNonNull(children)));
    }
}
