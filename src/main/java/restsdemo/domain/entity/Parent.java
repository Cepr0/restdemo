package restsdemo.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import restsdemo.domain.base.LongId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static javax.persistence.CascadeType.ALL;

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
    @OneToMany(cascade = ALL)
    private final List<Child> children = new ArrayList<>();
    
    public Parent(String name, Child... children) {
        this.name = requireNonNull(name);
        this.children.addAll(Arrays.asList(requireNonNull(children)));
    }
}
