package restsdemo.domain.entity;

import lombok.*;
import restsdemo.domain.base.LongId;

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
}
