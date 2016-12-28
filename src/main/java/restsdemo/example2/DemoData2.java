package restsdemo.example2;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Cepro, 2016-12-27
 */
@Service
@RequiredArgsConstructor
public class DemoData2 {

    private final OrderRepo orderRepo;
    
    @Async
    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        
        Order order = new Order(new ArrayList<>(Arrays.asList(
                new LineItem(null, "Title1", 1, BigDecimal.valueOf(5.0)),
                new LineItem(null, "Title2", 2, BigDecimal.valueOf(10.0)),
                new LineItem(null, "Title3", 3, BigDecimal.valueOf(15.0)))));

        orderRepo.saveAndFlush(order);
    }
}
