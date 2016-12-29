package restsdemo.example1;

import lombok.*;
import org.springframework.data.rest.core.config.Projection;
import restsdemo.base.LongId;

import javax.persistence.*;

/**
 * @author Cepro, 2016-12-25
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "children")
public class Child extends LongId {
    
    @NonNull
    @Column(nullable = false)
    private String name;
    
    @NonNull
    @ManyToOne(optional = false)
    private Reference reference;
    
    public Child(String reference) {
    }

    @Projection(name = "detailed", types = Child.class)
    interface Detailed {
        String getName();
        Reference getReference();
    }
}
