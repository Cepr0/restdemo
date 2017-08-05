package restsdemo.example16;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Cepro
 * @since 2017-08-03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/men")
public class ManController {

    private final @NonNull ManRepo manRepo;
    private final @NonNull ManService manService;
    
    @GetMapping
    public List<Man> getMen() {
        return manRepo.findAll();
    }
    
    @PostMapping
    public Man postMan(@RequestBody Man man) {
        manService.save(man);
        Man one = manService.get(man.getId());
        Way way = one.getWay();
        return one;
    }
}
