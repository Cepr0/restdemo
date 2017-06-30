package restsdemo.example6;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Cepro
 *         2017-06-30
 */
@RepositoryRestController
@RequestMapping("/users/roles")
public class RoleController {

    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        List<Resource<User.Role>> content = new ArrayList<>();
        content.addAll(Arrays.asList(
                new Resource<>(User.Role.ROLE1),
                new Resource<>(User.Role.ROLE2)));
        return ResponseEntity.ok(new Resources<>(content));
    }
}
