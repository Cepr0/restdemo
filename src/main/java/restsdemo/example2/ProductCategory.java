package restsdemo.example2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.config.Projection;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * @author Cepro
 *         2017-06-21
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_categories")
public class ProductCategory extends LongId {

    private String name;

//    @JsonBackReference
    @OneToMany(mappedBy = "category")
    private final Set<Product> products = new HashSet<>();

    public ProductCategory(String name, Product... products) {

        this.name = name;
        this.products.addAll(asList(products));
    }

    @Projection(name = "withProducts", types = ProductCategory.class)
    public interface WithProducts {

        String getName();
        Set<Product> getProducts();
    }
}
