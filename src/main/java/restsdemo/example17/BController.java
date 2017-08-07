package restsdemo.example17;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Cepro
 * @since 2017-08-07
 */
@RequiredArgsConstructor
@RepositoryRestController
public class BController {
    
    private final BRepo bRepo;
    
    @PostMapping("/bs")
    public ResponseEntity<?> post(@RequestBody Resource<B> bResource) {
        
        B b = bRepo.save(bResource.getContent());
        
        BProjection result = bRepo.findById(b.getId());
        
        return ResponseEntity.ok(new Resource<>(result));
    }
}
