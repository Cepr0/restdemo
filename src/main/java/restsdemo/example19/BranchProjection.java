package restsdemo.example19;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * @author Cepro
 *         2017-08-15
 */
@Projection(types = Branch.class, name = "withChildren")
public interface BranchProjection {
    String getName();
    List<Branch> getChildren();
}
