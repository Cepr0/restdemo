package restsdemo.example2;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import static java.math.BigDecimal.valueOf;

/**
 * @author Cepro, 2016-12-29
 */
@Component
@RepositoryEventHandler(Order.class)
public class OrderRepoEventHandler {
    
    @HandleBeforeCreate
    public void handleOrderBeforeCreate(Order order) {
        setDiscount(order);
    }
    
    @HandleBeforeSave
    public void handleOrderBeforeSave(Order order) {
        setDiscount(order);
    }

    private void setDiscount(Order order) {
        final double DISCOUNT = 0.90;
        order.getItems().forEach(
                item -> item.setPrice(item.getProduct().getPrice().multiply(valueOf(DISCOUNT)))
        );
    }
}
