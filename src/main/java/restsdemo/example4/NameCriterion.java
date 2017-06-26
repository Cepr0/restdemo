package restsdemo.example4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @RestResource(rel = "criteria", path = "criteria")
@Entity
public class NameCriterion extends Criterion {

    @NotBlank
    private String name = "Undefine";
}
