package restsdemo.example19;

import lombok.*;
import restsdemo.base.LongId;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Cepro
 *         2017-08-15
 */
@EqualsAndHashCode(exclude = "children", callSuper = true)
@ToString(exclude = "children", callSuper = true)
@Entity
public class Branch extends LongId {

    @Getter
    @Setter
    private String name;

    @Getter
    @OneToMany
    @JoinColumn(name = "parent_id")
    private Set<Branch> children = new HashSet<>();

    @Getter
    @ManyToOne
    private Branch parent;

    public Branch() {
    }

    public Branch(String name, Branch parent) {
        this.name = name;
        this.parent = parent;
    }

    public Branch addChild(@NonNull Branch child) {
        if (child.getParent() != this) {
            child.setParent(this);
        }
        children.add(child);
        return this;
    }

    public Branch removeChild(@NonNull Branch child) {
        if (child.getParent() == this && children.contains(child)) {
            children.remove(child);
            child.setParent(null);
        }
        return this;
    }

    public void setParent(Branch parent) {
        if (this.parent != null) {
            this.parent.removeChild(this);
        }

        this.parent = parent;

        if (parent != null) {
            parent.addChild(this);
        }
    }

    public void setChildren(Set<Branch> children) {

        // temporary set used for child iteration
        Set<Branch> tmp = new HashSet<>(this.children);

        // unlink and remove current children - affects on this.children so we need to use temporary set
        tmp.forEach(this::removeChild);

        // add new children
        children.forEach(this::addChild);
    }
}
