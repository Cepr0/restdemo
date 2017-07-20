package restsdemo.example2;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

import static java.util.Optional.ofNullable;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Cepro
 *         2017-07-17
 */
@Slf4j
@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/products")
public class ProductController {

    @NonNull
    private final ProductRepo productRepo;

    @NonNull
    private final PagedResourcesAssembler<Product.WithQuantity> assembler;

//    @ResponseBody
    @GetMapping("/report")
    public ResponseEntity<?> report(
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "date", required = false) LocalDate date,
            Pageable pageable) {
        LocalDate repDate = ofNullable(date).orElse(LocalDate.now());
        Page<Product.WithQuantity> products = productRepo.findAllByOrderDate(repDate, pageable);
        Resources<Product.WithQuantity> resources = new Resources<>(products);
//        Page<Product> products = productRepo.findAll(pageable);
//        return ok(assembler.toResource(products));
        return ok(resources);

//        return ok(productRepo.findAll());
//        return productRepo.findAll();
    }
}
