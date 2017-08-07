package restsdemo.example17;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author Cepro
 * @since 2017-08-07
 */
@Projection(name = "withA", types = B.class)
public interface BProjection {
    
    @Value("#{target.a}")
    A getA();
    
    String getSomeData();
    
    String getOtherData();
}
