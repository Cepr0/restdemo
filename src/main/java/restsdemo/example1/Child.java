package restsdemo.example1;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.rest.core.config.Projection;
import restsdemo.base.LongId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

/**
 * @author Cepro, 2016-12-25
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "children")
public class Child extends LongId {
    
    @NotBlank
    @Column(nullable = false)
    private String name;
    
    @ManyToOne(optional = false)
    private Reference reference;
    
    @OneToMany(cascade = {PERSIST, MERGE})
    private final List<Toy> toys = new ArrayList<>();
    
    public Child(String reference) {
    }
    
    public Child(String name, Reference reference, Toy... toys) {
        this.name = name;
        this.reference = reference;
        this.toys.addAll(asList(toys));
    }
    
    @Projection(name = "detailed", types = Child.class)
    interface Detailed {
        String getName();
        Reference getReference();
        List<Toy> getToys();
    }
}
