package restsdemo.example2;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author Cepro, 2016-12-29
 */
@Component
@RepositoryEventHandler(Order.class)
public class OrderRepoEventHandler {
    
    @HandleBeforeCreate
    public void handleOrderBeforeCreate(Order order) {
        System.out.println("!!!!! BeforeCreate Order " + order.toString());
    }
    
    @HandleBeforeSave
    public void handleOrderBeforeSave(Order order) {
        System.out.println("!!!!! BeforeSave Order " + order.toString());
    }
}
