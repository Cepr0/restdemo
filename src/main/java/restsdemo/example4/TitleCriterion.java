package restsdemo.example4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

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
public class TitleCriterion extends Criterion {

    @NotBlank
    private String title = "Undefine";
}
