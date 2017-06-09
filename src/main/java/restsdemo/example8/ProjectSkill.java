package restsdemo.example8;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 */
@Data
@Embeddable
public class ProjectSkill {

    @OneToOne(optional = false)
    @JoinColumn(unique = true)
    private Skill skill;

    @Min(1)
    @Max(5)
    private Integer minLevel;
}
