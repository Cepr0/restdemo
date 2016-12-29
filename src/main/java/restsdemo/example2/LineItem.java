package restsdemo.example2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Cepro, 2016-12-27
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "items")
public class LineItem extends LongId {
    
    @ManyToOne
    private Order order;
    
    private String title;
    
    private Integer quantity;
    
    private BigDecimal price;
}
