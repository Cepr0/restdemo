package restsdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

/**
 * Repository REST Resource processors of any entities. Dev purposes only.
 * @author Cepro, 2017-01-28
 */
@Slf4j
@Component
public class ResourceProcessors {
    
    @Component
    public class SingleResourceProcessor implements ResourceProcessor<Resource<?>> {
    
        @Override
        public Resource<?> process(Resource<?> resource) {
            return resource;
        }
    }
    
    @Component
    public class MultiResourceProcessor implements ResourceProcessor<Resources<Resource<?>>> {
    
        @Override
        public Resources<Resource<?>> process(Resources<Resource<?>> resource) {
            return resource;
        }
    }
    
    @Component
    public class PagedResourceProcessor implements ResourceProcessor<PagedResources<Resource<?>>> {
    
        @Override
        public PagedResources<Resource<?>> process(PagedResources<Resource<?>> resource) {
            return resource;
        }
    }
}
