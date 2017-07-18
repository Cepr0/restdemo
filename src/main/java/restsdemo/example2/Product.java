package restsdemo.example2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author Cepro, 2016-12-29
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@JsonInclude(NON_NULL)
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

    @JsonInclude(NON_NULL)
    public interface WithQuantity {

        @JsonProperty("product")
        Product getProduct();

        @JsonProperty("quantity")
        Integer getQuantity();

        @JsonProperty("total")
        BigDecimal getTotal();
    }

    @JsonInclude(NON_NULL)
    public interface Short {

        @JsonProperty("name")
        Product getName();

        @JsonProperty("quantity")
        Integer getQuantity();

        @JsonProperty("total")
        BigDecimal getTotal();
    }
}
