package restsdemo.example2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.config.Projection;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Cepro, 2016-12-29
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends LongId {
    
    private String name;
    
    private BigDecimal price;

    @ManyToOne
    private ProductCategory category;

    @Projection(name = "withCategory", types = Product.class)
    public interface WithCategory {

        String getName();
        BigDecimal getPrice();
        ProductCategory getCategory();
    }
}
