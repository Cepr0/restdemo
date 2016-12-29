package restsdemo.example2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.AccessType.*;

/**
 * @author Cepro, 2016-12-27
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Access(PROPERTY)
@Table(name = "items")
public class LineItem extends LongId {
    
    private Order order;
    
    private String title;
    
    private Integer quantity;
    
    private BigDecimal price;
    
    @ManyToOne
    public Order getOrder() {
        return order;
    }
}
