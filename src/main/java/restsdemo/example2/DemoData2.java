package restsdemo.example2;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;

/**
 * @author Cepro, 2016-12-27
 */
@Service
@RequiredArgsConstructor
public class DemoData2 {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final ProductCategoryRepo categoryRepo;
    
    @Async
    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {

        List<ProductCategory> categories = categoryRepo.save(asList(
                new ProductCategory("Category1"),
                new ProductCategory("Category1")
        ));

        Product p1 = new Product("Product1", valueOf(1.0), categories.get(0));
        Product p2 = new Product("Product2", valueOf(2.0), categories.get(0));
        Product p3 = new Product("Product3", valueOf(3.0), categories.get(1));
        Product p4 = new Product("Product4", valueOf(4.0), categories.get(1));
        Product p5 = new Product("Product5", valueOf(5.0), categories.get(1));

        productRepo.save(asList(p1, p2, p3, p4, p5));
        productRepo.flush();

        Order order = new Order(asList(
                new LineItem(p1, 1),
                new LineItem(p2, 2),
                new LineItem(p3, 3)));

        orderRepo.saveAndFlush(order);
    }
}
