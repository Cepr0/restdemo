package restsdemo.example2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Cepro, 2016-12-29
 */
@RepositoryRestResource
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("select p as product, sum(i.quantity) as quantity, sum(i.quantity * i.price) as total from Order o join o.items i join i.product p where o.registered between ?1 and ?2 group by p")
    List<Product.WithQuantity> findAllByOrderBetweenDateTime(LocalDateTime from, LocalDateTime to);

    default List<Product.WithQuantity> findAllByOrderDate(LocalDate date) {
        return findAllByOrderBetweenDateTime(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }
}
