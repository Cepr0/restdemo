package restsdemo.domain.entity;

import lombok.*;
import restsdemo.domain.base.LongId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    
//    @ManyToOne(optional = false)
//    @NonNull
//    private Reference reference;
}
