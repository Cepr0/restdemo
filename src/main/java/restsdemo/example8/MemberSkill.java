package restsdemo.example8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 */
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Embeddable
public class MemberSkill {

    @OneToOne(optional = false)
    @JoinColumn(unique = true)
    private final Skill skill;

    @Min(1)
    @Max(5)
    private final Integer level;
}
