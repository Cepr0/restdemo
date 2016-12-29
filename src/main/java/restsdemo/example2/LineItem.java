package restsdemo.example2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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
    
    @ManyToOne(optional = false)
    private Product product;
    
    private Integer quantity;
    
    @JsonProperty(value = "name", access = READ_ONLY, index = 0)
    public String getName() {
        if(product != null) {
            return product.getName();
        } else {
            return null;
        }
    }
    
    @JsonProperty(value = "price", access = READ_ONLY)
    public BigDecimal getPrice() {
        if (product != null) {
            return product.getPrice();
        } else {
            return ZERO;
        }
    }
    
    @JsonProperty(value = "total", access = READ_ONLY)
    public BigDecimal getTotal() {
        return getPrice().multiply(valueOf(quantity));
    }
}
