package restsdemo.example12;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Cepro
 * @since 2017-07-12
 */
@Component
public class OneResourceProcessor implements ResourceProcessor<Resource<One>> {
    
    @Override
    public Resource<One> process(Resource<One> resource) {
        resource.add(new Link("/foo").withRel("foo"));
        return resource;
    }
}
