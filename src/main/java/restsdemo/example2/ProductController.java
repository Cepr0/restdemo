package restsdemo.example2;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Cepro
 *         2017-07-17
 */
@Slf4j
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/orders")
public class ProductController {

    @NonNull
    private final ProductRepo productRepo;

//    @ResponseBody
    @GetMapping("/report")
    public ResponseEntity<?> report(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "date", required = false) LocalDate date) {
        LocalDate repDate = ofNullable(date).orElse(LocalDate.now());
        List<Product.WithQuantity> products = productRepo.findAllByOrderDate(repDate);
        // Resources<Product.WithQuantity> resources = new Resources<>(products);
        return ok(products);
    }
}
