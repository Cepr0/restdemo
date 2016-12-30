package restsdemo.example2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.util.Objects.requireNonNull;

/**
 * @author Cepro, 2016-12-27
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "items")
public class LineItem extends LongId {
    
    @ManyToOne
    private Order order;
    
    @ManyToOne(optional = false)
    private Product product;
    
    private Integer quantity = 0;
    
    @JsonProperty(access = READ_ONLY)
    private BigDecimal price = ZERO;
    
    public LineItem(Product product, Integer quantity) {
        this.product = requireNonNull(product);
        this.price = product.getPrice();
        this.quantity = requireNonNull(quantity);
    }
    
    @JsonProperty(value = "name", access = READ_ONLY)
    public String getName() {
        if(product != null) {
            return product.getName();
        } else {
            return null;
        }
    }
    
    @JsonProperty(value = "cost", access = READ_ONLY)
    public BigDecimal getCost() {
        return getPrice().multiply(valueOf(quantity));
    }
}
