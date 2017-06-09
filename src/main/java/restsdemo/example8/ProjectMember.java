package restsdemo.example8;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 */
@Data
@Embeddable
public class ProjectMember {

    @OneToOne(optional = false)
    @JoinColumn(unique = true)
    private Role role;

    @OneToOne(optional = false)
    @JoinColumn(unique = true)
    private Member member;
}
